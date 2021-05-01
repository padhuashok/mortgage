package com.cognizant.mortgage;

import java.time.LocalDate;
import java.util.*;

public class MortgageApp {
    public double approvedLoanAmount;
    public String loanStatus;
    public LocalDate loanApprovalDate;
    private int loanId;
    private int customerId;

    public HashMap<String,List<MortgageApp>> listOfLoans = new HashMap<>();

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
                loanStatus="approved";
                customer.setCustomerLoanStatus("approved");
            }
            else
            {
                loanStatus="onhold";
                customer.setCustomerLoanStatus("approved");
            }

        }
        return loanStatus;
    }
    public void updateApplicationAndFundStatus(Customer customer, Lender lender)
    {

        if(checkLoanQualification(customer,lender).equals("approved"))
        {
            double pendingfund = customer.getCustomerLoanAmount();
            lender.setPendingFunds(lender.getPendingFunds()+pendingfund);
            lender.setAvailableFunds(lender.getAvailableFunds()-pendingfund);

        } else if (checkLoanQualification(customer,lender).equals("accepted")){
                lender.setPendingFunds(lender.getPendingFunds()-customer.getCustomerLoanAmount());
                loanStatus = "accepted";
        } else if(checkLoanQualification(customer,lender).equals("rejected")){
            lender.setAvailableFunds(lender.getAvailableFunds() + customer.getCustomerLoanAmount());
            lender.setPendingFunds(lender.getPendingFunds()- customer.getCustomerLoanAmount());
            loanStatus = "rejected";
        }
    }
    public void updateStatusToExpired(Customer customer,Lender lender){
        LocalDate expiredDate = LocalDate.now().minusDays(3);
        System.out.println("approval date - " +loanApprovalDate.toString());
        System.out.println("calculated date - " +expiredDate.toString());
        if(!expiredDate.equals(loanApprovalDate)){
            lender.setPendingFunds(lender.getPendingFunds()- customer.getCustomerLoanAmount());
            lender.setAvailableFunds(lender.getAvailableFunds() + customer.getCustomerLoanAmount());
            loanStatus = "expired";
        }
    }
    public List<MortgageApp> getLoanByStatus(String status,HashMap<String,List<MortgageApp>> myLoanMap){
        if(myLoanMap.containsKey(status)){
            return myLoanMap.get(status);
        }
        return new ArrayList<MortgageApp>();
    }

        }
