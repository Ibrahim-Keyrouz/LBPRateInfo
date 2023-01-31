package com.boteam.sou2sawda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class RateResponse {
    @JsonProperty("lirarate")
    private LiraRate liraRate ;

    @JsonProperty("fuel")
    private ArrayList<Fuel> fuel;
    @JsonProperty("omt")
    private ArrayList<ArrayList<Object>> omt;

    //   public ArrayList<ArrayList<Object>> sayrafa;

    @JsonIgnore
    private HttpStatus errorCode;

    public RateResponse() {

    }



    public LiraRate getLiraRate() {
        return liraRate;
    }

    public void setLiraRate(LiraRate liraRate) {
        this.liraRate = liraRate;
    }

    public ArrayList<ArrayList<Object>>  getOmt() {
        return omt;
    }

    public void setOmt(ArrayList<ArrayList<Object>> omt) {
        this.omt = omt;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<Fuel> getFuel() {
        return fuel;
    }

    public void setFuel(ArrayList<Fuel> fuel) {
        this.fuel = fuel;
    }
}
