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

    public String checkLoanQualification(Customer customer,Lender lender) {
        loanStatus = "rejected";
        if(checkCustomerQualification(customer).equals("qualified"))
        {
            if(lender.getAvailableFunds()>=customer.getCustomerLoanAmount())
            {
                //loanStatus="approved";
                customer.setCustomerLoanStatus("approved");
            }
            else
            {
                loanStatus="onhold";
                customer.setCustomerLoanStatus("onhold");
            }

        }
        return customer.getCustomerLoanStatus();
    }
    public void updateApplicationandFundStatus(Customer customer, Lender lender)
    {

        if(checkLoanQualification(customer,lender).equals("approved"))
        {
            double pendingfund = customer.getCustomerLoanAmount();
            lender.setPendingFunds(lender.getPendingFunds()+pendingfund);
            lender.setAvailableFunds(lender.getAvailableFunds()-pendingfund);

        }
    }
}
