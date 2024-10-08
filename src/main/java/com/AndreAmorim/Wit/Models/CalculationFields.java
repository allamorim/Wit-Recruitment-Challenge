package com.AndreAmorim.Wit.Models;

public class CalculationFields {

    double FirstTerm;
    double SecondTerm;

    public CalculationFields(double firstTerm, double secondTerm) {
        FirstTerm = firstTerm;
        SecondTerm = secondTerm;
    }

    public double getFirstTerm() {
        return FirstTerm;
    }

    public double getSecondTerm() {
        return SecondTerm;
    }

}
