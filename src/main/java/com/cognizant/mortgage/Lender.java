package com.cognizant.mortgage;

public class Lender {
    double availableFunds;
    double pendingFunds;

    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }

    public double getPendingFunds() {
        return pendingFunds;
    }

    public void setPendingFunds(double pendingFunds) {
        this.pendingFunds = pendingFunds;
    }

    public void addFund(double depositAmt){
        this.availableFunds += depositAmt;
    }

}
