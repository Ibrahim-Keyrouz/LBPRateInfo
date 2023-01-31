package com.boteam.sou2sawda.service;

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
import java.util.Date;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class RateServiceImpl implements RateService{


    @Override
    public RateResponse fetch() {

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
                    .queryParam("_ver", "t"+new SimpleDateFormat("yyyyMddHH").format(new Date()))

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
                return rateResponse;
            } else if (rateResp.getStatusCode().is4xxClientError()) {
                rateResponse.setErrorCode(rateResp.getStatusCode());
                return rateResponse;
            } else if (rateResp.getStatusCode().is5xxServerError()) {
                rateResponse.setErrorCode(rateResp.getStatusCode());
                return rateResponse;
            }
            rateResponse.setErrorCode(rateResp.getStatusCode());
            return rateResponse;
        }catch (Exception e) {
            rateResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println(e);
            return rateResponse;
        }
    }
}
