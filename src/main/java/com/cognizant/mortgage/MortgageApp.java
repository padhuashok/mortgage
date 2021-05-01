package com.cognizant.mortgage;

public class MortgageApp {
    public double approvedLoanAmount;
    public String loanStatus;
    public String checkCustomerQualification(Customer customer){
        if (customer.getCustomerDti()>36
                ||
            customer.getCustomerCreditScore()<620)
        {
            customer.setCustomerQualification("not qualified");
        }
        else if ( customer.getCustomerSavings()>=(customer.getCustomerLoanAmount()*.25))
                {
                    customer.setCustomerQualification("qualified");
                }
                else
                {
                    customer.setCustomerQualification("partially qualified");
                }
            return customer.getCustomerQualification();
           }
}
