package com.boteam.sou2sawda.model;

import java.math.BigDecimal;

public class ArrayDetails {
        private BigDecimal nbr;
        private int usdRateToLBP;

    public ArrayDetails(BigDecimal nbr, int usdRateToLBP) {
        this.nbr = nbr;
        this.usdRateToLBP = usdRateToLBP;
    }

    public BigDecimal getNbr() {
        return nbr;
    }

    public void setNbr(BigDecimal nbr) {
        this.nbr = nbr;
    }

    public int getUsdRateToLBP() {
        return usdRateToLBP;
    }

    public void setUsdRateToLBP(int usdRateToLBP) {
        this.usdRateToLBP = usdRateToLBP;
    }
}
