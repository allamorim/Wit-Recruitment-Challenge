package com.AndreAmorim.Wit.Models;

import java.math.BigDecimal;

public class CalculationFields {

    private BigDecimal firstTerm = BigDecimal.ZERO;
    private BigDecimal secondTerm = BigDecimal.ZERO;

    public CalculationFields() {}

    public CalculationFields(BigDecimal firstTerm, BigDecimal secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public BigDecimal getFirstTerm() {
        return firstTerm;
    }

    public BigDecimal getSecondTerm() {
        return secondTerm;
    }

}
