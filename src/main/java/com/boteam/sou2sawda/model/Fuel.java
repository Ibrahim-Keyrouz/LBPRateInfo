package com.boteam.sou2sawda.model;

import java.util.ArrayList;

public class Fuel {
    private String name;
    private ArrayList<ArrayList<Object>> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Object>> getData() {
       return data;
    }

    public void setData(ArrayList<ArrayList<Object>> data) {
        this.data = data;
    }


}
