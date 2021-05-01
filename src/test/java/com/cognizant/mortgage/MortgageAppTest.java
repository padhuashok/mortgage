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
}
