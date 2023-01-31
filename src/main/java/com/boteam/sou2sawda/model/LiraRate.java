package com.boteam.sou2sawda.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

public class LiraRate  {
    @JsonProperty("buy")
    private ArrayList<ArrayList<Object>> buy ;

    @JsonProperty("sell")
    private ArrayList<ArrayList<Object>> sell;

    public ArrayList<ArrayList<Object>> getBuy() {
        return buy;
    }

    public void setBuy(ArrayList<ArrayList<Object>> buy) {
        this.buy = buy;
    }

    public ArrayList<ArrayList<Object>> getSell() {
        return sell;
    }

    public void setSell(ArrayList<ArrayList<Object>> sell) {
        this.sell = sell;
    }
}
