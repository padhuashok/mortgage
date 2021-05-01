package com.cognizant.mortgage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageAppTest {

    @Test
    void testAvailableFunds(){
       // @BeforeEach
//        void setup() {
//
//        }
        //setup
        MortgageApp myApp1 = new MortgageApp();
        Lender lender1 = new Lender();
        //execute
        double expectedFunds = 500000;
        lender1.setAvailableFunds(500000);
        //assert
        assertEquals(expectedFunds,lender1.getAvailableFunds());

    }

    @Test
    void testAddFundsAndCheckTotal(){
        //setup
        Lender lender1 = new Lender();
        //execute
        lender1.setAvailableFunds(125000);
        lender1.addFund(0);
        double expectedFunds = 125000;
        //assert
        assertEquals(expectedFunds,lender1.getAvailableFunds());
    }

    @Test
    void testCheckCustomerQualification(){
        //
        //setup
        Customer customer1 = new Customer();
        customer1.setCustomerDti(36);
        customer1.setCustomerCreditScore(620);
        customer1.setCustomerSavings(100000);
        customer1.setCustomerLoanAmount(400000);
        //execute
        MortgageApp mortgageapp1 = new MortgageApp();

        //assert
        String expected ="qualified";
        String actual = mortgageapp1.checkCustomerQualification(customer1);
        assertEquals(expected,actual);
        //
        //setup
        Customer customer2 = new Customer();
        customer2.setCustomerDti(37);
        customer2.setCustomerCreditScore(619);
        customer2.setCustomerSavings(100000);
        customer2.setCustomerLoanAmount(400000);
        //assert
        expected ="not qualified";
        actual = mortgageapp1.checkCustomerQualification(customer2);
        assertEquals(expected,actual);
        //setup
        Customer customer3 = new Customer();
        customer3.setCustomerDti(36);
        customer3.setCustomerCreditScore(620);
        customer3.setCustomerSavings(100000);
        customer3.setCustomerLoanAmount(500000);
        //assert
        expected ="partially qualified";
        actual = mortgageapp1.checkCustomerQualification(customer3);
        assertEquals(expected,actual);
    }
    @Test
    void testCheckLoanQualification()
    {
        //setup
        Customer customer4 = new Customer();
        MortgageApp mortgageapp= new MortgageApp();
        Lender lender1 = new Lender();

        lender1.setAvailableFunds(500000);
        customer4.setCustomerDti(36);
        customer4.setCustomerCreditScore(700);
        customer4.setCustomerSavings(200000);
        customer4.setCustomerLoanAmount(500000);
        //execute
        String expected ="approved";
        String actual = mortgageapp.checkLoanQualification(customer4,lender1);
        //assert
        assertEquals(expected,actual);
        //completed approved case
        //for on hold case
        //setup
        Customer customer5 = new Customer();
        MortgageApp mortgageapp1= new MortgageApp();
        Lender lender2 = new Lender();

        lender2.setAvailableFunds(500000);
        customer5.setCustomerDti(36);
        customer5.setCustomerCreditScore(700);
        customer5.setCustomerSavings(200000);
        customer5.setCustomerLoanAmount(600000);
        //execute
        expected ="onhold";
        actual = mortgageapp.checkLoanQualification(customer5,lender2);
        //assert
        assertEquals(expected,actual);
        // completed onhold case

        //for on rejected case
        //setup
        Customer customer6 = new Customer();
        MortgageApp mortgageapp3= new MortgageApp();
        Lender lender3 = new Lender();

        lender3.setAvailableFunds(500000);
        customer6.setCustomerDti(36);
        customer6.setCustomerCreditScore(700);
        customer6.setCustomerSavings(100000);
        customer6.setCustomerLoanAmount(600000);
        //execute
        expected ="rejected";
        actual = mortgageapp.checkLoanQualification(customer6,lender3);
        //assert
        assertEquals(expected,actual);
        // completed onhold case

    }

    @Test
    void testUpdateApplicationandFundStatus(){
        //test for pending,available funds
        //setup
        Customer customer7 = new Customer();
        customer7.setCustomerLoanAmount(200000);
        customer7.setCustomerLoanStatus("approved");

        Lender lender4 = new Lender();
        lender4.setAvailableFunds(500000);
        lender4.setPendingFunds(100000);

        MortgageApp mortgageapp4= new MortgageApp();
        //execution
        double expected = 700000;
        mortgageapp4.updateApplicationAndFundStatus(customer7,lender4);
        double actual = lender4.getAvailableFunds();
        //
        assertEquals(expected,actual);

        //given a loan is approved , when customer accepts the offer, then update the pending and available funds
        lender4 = new Lender();
        lender4.setAvailableFunds(250000);
        lender4.setPendingFunds(300000);
        customer7 = new Customer();
        customer7.setCustomerLoanAmount(200000);
        customer7.setCustomerLoanStatus("accepted");

        double expectedPendingFund = 100000;
        double expectedAvailableFund = 450000;
        mortgageapp4.updateApplicationAndFundStatus(customer7,lender4);
        assertEquals(expectedPendingFund,lender4.getPendingFunds());
        assertEquals(expectedAvailableFund,lender4.getAvailableFunds());

        //given a loan is approved , when customer rejects the offer, then update the pending and available funds
        lender4 = new Lender();
        lender4.setAvailableFunds(250000);
        lender4.setPendingFunds(300000);
        customer7 = new Customer();
        customer7.setCustomerLoanAmount(200000);
        customer7.setCustomerLoanStatus("rejected");

        expectedPendingFund = 100000;
        expectedAvailableFund = 450000;
        mortgageapp4.updateApplicationAndFundStatus(customer7,lender4);
        assertEquals(expectedPendingFund,lender4.getPendingFunds());
        assertEquals(expectedAvailableFund,lender4.getAvailableFunds());

    }

    @Test
    void testExpiredApplicationStatus(){
        //set up
        MortgageApp mortgageapp4= new MortgageApp();
        mortgageapp4.loanApprovalDate = LocalDate.of(2021, Month.APRIL,24);
        Customer customer7 = new Customer();
        customer7.setCustomerLoanAmount(200000);
        customer7.setCustomerLoanStatus("approved");

        Lender lender4 = new Lender();
        lender4.setAvailableFunds(500000);
        lender4.setPendingFunds(300000);

        //given the loan status is approved ;when the loan approved date is 3 days ago, then status goes to expired
        mortgageapp4.updateStatusToExpired(customer7,lender4);
        assertEquals("expired",mortgageapp4.loanStatus);
        assertEquals(100000,lender4.pendingFunds);
        assertEquals(700000,lender4.availableFunds);
    }

    //@Test
    void testGetLoansByStatus(){
        List<MortgageApp> myLoans = new ArrayList<MortgageApp>();
        MortgageApp m1 = new MortgageApp();
        m1.loanStatus = "approved";
        m1.loanApprovalDate = LocalDate.now();
        m1.approvedLoanAmount = 300000;
        MortgageApp m2 = new MortgageApp();
        m2.loanStatus = "rejected";
        m2.loanApprovalDate = LocalDate.now();
        m2.approvedLoanAmount = 300000;
        MortgageApp m3 = new MortgageApp();
        m3.loanStatus = "expired";
        m3.loanApprovalDate = LocalDate.now();
        m3.approvedLoanAmount = 300000;
        MortgageApp m4 = new MortgageApp();
        m4.loanStatus = "accepted";
        m4.loanApprovalDate = LocalDate.now();
        m4.approvedLoanAmount = 300000;
        MortgageApp m5 = new MortgageApp();
        m5.loanStatus = "denied";
        m5.loanApprovalDate = LocalDate.now();
        m5.approvedLoanAmount = 300000;
        myLoans.add(m1);
        myLoans.add(m2);
        myLoans.add(m3);
        myLoans.add(m4);
        myLoans.add(m5);

        HashMap<String,List<MortgageApp>> listMap = new HashMap<>();
        for (MortgageApp myLoansList: myLoans
             ) {
            if(listMap.containsKey(myLoansList.loanStatus)){
                listMap.get(myLoansList.loanStatus).add(myLoansList);
            }else
                listMap.put(myLoansList.loanStatus, myLoans);
             }
        //assertEquals();
    }
}
