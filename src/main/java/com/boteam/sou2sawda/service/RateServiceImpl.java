package com.boteam.sou2sawda.service;

import com.boteam.sou2sawda.model.FinalFuel;
import com.boteam.sou2sawda.model.FinalResponse;
import com.boteam.sou2sawda.model.Fuel;
import com.boteam.sou2sawda.model.RateResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import java.math.BigDecimal;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class RateServiceImpl implements RateService{


    @Override
    public FinalResponse fetch() {

        FinalResponse fr = new FinalResponse();

        Properties props = System.getProperties();

        props.put("https.proxyHost", "172.30.35.145");
        props.put("https.proxyPort", "8080");
        RestTemplate restTemplate = new RestTemplate();
        RateResponse rateResponse = new RateResponse();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            String url = "https://lirarate.org/wp-json/lirarate/v2/all" ;

            String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("currency", "LBP")
                    .queryParam("_ver", "t"+new SimpleDateFormat("yyyyMdH").format(new Date()))

                    .encode()
                    .toUriString();


            System.out.println(urlTemplate);
            ResponseEntity<RateResponse> rateResp = restTemplate.exchange(
                    urlTemplate,
                    HttpMethod.GET,
                    entity,
                    RateResponse.class

            );
            if (rateResp.getStatusCode().equals(HttpStatus.OK)) {

                rateResponse.setLiraRate(rateResp.getBody().getLiraRate());
                rateResponse.setOmt(rateResp.getBody().getOmt());
                rateResponse.setErrorCode(rateResp.getStatusCode());
                ArrayList<Object> buy =  rateResp.getBody().getLiraRate().getBuy().get(rateResp.getBody().getLiraRate().getBuy().size() - 1);
                ArrayList<Object> sell =  rateResp.getBody().getLiraRate().getSell().get(rateResp.getBody().getLiraRate().getSell().size() - 1);
                ArrayList<Object> omt =  rateResp.getBody().getOmt().get(rateResp.getBody().getOmt().size() - 1);

                fr.setDate(new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z").format(new Date((Long)buy.get(0))));
                fr.setBuy((Integer)buy.get(buy.size() - 1));
                fr.setSell((Integer)sell.get(sell.size() - 1));
                fr.setOmt((Integer)omt.get(omt.size() - 1));
                fr.setErrorCode(rateResp.getStatusCode());

                ArrayList<FinalFuel> fuel = new ArrayList<>();
                for (Fuel f : rateResp.getBody().getFuel()) {
                    FinalFuel finalFuel = new FinalFuel();
                    ArrayList<Object> data = f.getData().get(f.getData().size() - 1);
                    finalFuel.setPrice(new BigDecimal(String.valueOf(data.get(data.size() - 1))));
                    finalFuel.setProduct(f.getName());
                    fuel.add(finalFuel);
                }

                fr.setFuel(fuel);

                return fr;
            } else if (rateResp.getStatusCode().is4xxClientError()) {
                rateResponse.setErrorCode(rateResp.getStatusCode());
                fr.setErrorCode(rateResp.getStatusCode());
                return fr;
            } else if (rateResp.getStatusCode().is5xxServerError()) {
                rateResponse.setErrorCode(rateResp.getStatusCode());
                fr.setErrorCode(rateResp.getStatusCode());
                return fr;
            }
            rateResponse.setErrorCode(rateResp.getStatusCode());
            fr.setErrorCode(rateResp.getStatusCode());
            return fr;
        }catch (Exception e) {
            rateResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
            fr.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println(e);
            return fr;
        }
    }
}
