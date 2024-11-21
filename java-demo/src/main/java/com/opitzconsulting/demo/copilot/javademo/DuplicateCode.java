package com.opitzconsulting.demo.copilot.javademo;

public class DuplicateCode {

    /**
     * move repeated code to a method
     */
    void doSomething() {
        var totalSales = 0;

        var applePrice = 3;
        var applesSold = 100;
        totalSales += applePrice * applesSold;

        var orangePrice = 5;
        var orangesSold = 50;
        totalSales += orangePrice * orangesSold;
    }
}
