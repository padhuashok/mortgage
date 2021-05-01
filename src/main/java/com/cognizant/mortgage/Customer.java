package com.cognizant.mortgage;

public class Customer {
    private int customerNumber;
    private String customerName;
    private int customerDti;
    private int customerCreditScore;
    private double customerSavings;
    private String customerQualification;
    private double customerLoanAmount;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerDti() {
        return customerDti;
    }

    public void setCustomerDti(int customerDti) {
        this.customerDti = customerDti;
    }

    public int getCustomerCreditScore() {
        return customerCreditScore;
    }

    public void setCustomerCreditScore(int customerCreditScore) {
        this.customerCreditScore = customerCreditScore;
    }

    public double getCustomerSavings() {
        return customerSavings;
    }

    public void setCustomerSavings(double customerSavings) {
        this.customerSavings = customerSavings;
    }

    public String getCustomerQualification() {
        return customerQualification;
    }

    public void setCustomerQualification(String customerQualification) {
        this.customerQualification = customerQualification;
    }

    public double getCustomerLoanAmount() {
        return customerLoanAmount;
    }

    public void setCustomerLoanAmount(double customerLoanAmount) {
        this.customerLoanAmount = customerLoanAmount;
    }
}
