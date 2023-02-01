package com.boteam.sou2sawda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class FinalResponse {

    private String date;

private Integer buy;

private Integer sell;

private Integer omt;

private ArrayList<FinalFuel> fuel;

@JsonIgnore
private HttpStatus errorCode;

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Integer getOmt() {
        return omt;
    }

    public void setOmt(Integer omt) {
        this.omt = omt;
    }

    public ArrayList<FinalFuel> getFuel() {
        return fuel;
    }

    public void setFuel(ArrayList<FinalFuel> fuel) {
        this.fuel = fuel;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
