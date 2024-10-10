package com.AndreAmorim.Wit.Models;

public class CalculationFields {

    private double firstTerm = 0;
    private double secondTerm = 0;

    public CalculationFields() {

    }

    public CalculationFields(double firstTerm, double secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public double getFirstTerm() {
        return firstTerm;
    }

    public double getSecondTerm() {
        return secondTerm;
    }

}
