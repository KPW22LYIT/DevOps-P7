/*
 * Person
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
import java.util.HashMap;

public class Person {

    private String name;

    //hash map of accounts and currency
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    //create an instance of a person
    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        addAccount("EUR");
    }

    //set the count balance and currency of it
    public void setAccountBalance(double startingBalance, String currency) {
        userAccounts.get(currency).setBalance(startingBalance);
    }

    //get the account balance
    public double getAccountBalance(String currency) {
        return userAccounts.get(currency).getBalance();
    }

    //get the account
    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    //calculate the currency exchange rate
    public double getCalculateCurrency(String account, double amount, String currencyCode) {
        return userAccounts.get(account).calculateCurrency(amount,currencyCode);
    }

    //add a new account
    public Account addAccount(String currency) {
        Currency accCurrency = Currency.getInstance(currency);
        Account account = new Account(accCurrency, 0);
        return userAccounts.put(currency, account);
    }

    //get the name of the person
    public String getName() {
        return name;
    }

    //get the existing contacts
    public String getContact(String name) {
        Contact myContact = new Contact(name);
        if(myContact.isValidRevolutContact(name)){

            return name;
        }else {
            return "No Revolut Customer";
        }

    }



}
