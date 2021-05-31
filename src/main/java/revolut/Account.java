/*
 * Account
 *
 * Version information
 *
 * Date 30/05/2021
 *
 * Author: Kevin Page-Wood and L00162107
 *
 * Copyright notice
 */
package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;
    private PaymentService paymentService;

    //Create instance of account
    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    //set the account balance
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    //get the latest account balance
    public double getBalance() {
        return this.balance;
    }

    //add funds to account
    public void addFunds(double topUpAmount, String paymentService) {

        if(paymentService.equals("sufficientFunds") || paymentService.isBlank()){
            //|| confirmFundsAvailable(topUpAmount)){
            this.balance += topUpAmount;
        }
    }

    //confirm funds in account
    public boolean confirmFundsAvailable(double requestedAmount) {
        if(requestedAmount > getBalance()){
            paymentService.setResponseFromService("sufficientFunds");
            return true;
        }else{
            paymentService.setResponseFromService("insufficientFunds");
            return false;
        }
    }

        //calculate the exchange rate. this could be a call to a web service
    public double calculateCurrency(double exchangeAmount, String currency) {
        double exchangeRate = 0;
        switch (currency) {
            case "GBP":
                System.out.println("GBP set");
                exchangeRate = 0.88;
                break;
            case "USD":
                System.out.println("USD set");
                exchangeRate = 1.2;
                break;
            case "JPY":
                System.out.println("YEN set");
                exchangeRate = 133.94;
                break;
            default:
                System.out.println("Default set");
                exchangeRate = 1.0;
        }

        //return converted exchange rate
        return exchangeAmount * exchangeRate;
    }

}
