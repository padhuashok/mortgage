package com.cognizant.mortgage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        double expected = 300000;
        mortgageapp4.updateApplicationandFundStatus(customer7,lender4);
        double actual = lender4.getAvailableFunds();
        //
        assertEquals(expected,actual);
    }
}
