package com.boteam.sou2sawda.model;

import java.util.ArrayList;

public class Omt  {
    private ArrayList<Object> omtDetails ;

    public Omt(ArrayList<Object> omtDetails) {
        this.omtDetails = omtDetails;
    }

    public ArrayList<Object> getOmtDetails() {
        return omtDetails;
    }

    public void setOmtDetails(ArrayList<Object> omtDetails) {
        this.omtDetails = omtDetails;
    }
}
