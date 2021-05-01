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
    void testCheckLoanQualification(){
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
}
