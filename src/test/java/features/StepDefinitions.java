package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;
import revolut.Vendors;


public class StepDefinitions {

    private double topUpAmount;
    private double exchangeAmount;
    private double sendToFriendAmount;
    private String currency;
    PaymentService topUpMethod;
    PaymentService bankAccount;
    Person danny;
    Person peter;
    Person contact;
    Vendors firstVendor;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        peter = new Person("Peter");
        firstVendor = new Vendors("Tesco");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //set the starting balance
        danny.setAccountBalance(startingBalance,"EUR");
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // set the top up amount
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // apply the payment source
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // top up with a response from payment service

            danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod.getResponseFromService());

    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {

        //Test the new balance of the account
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOfStartBalance(double startingBalance) {
        //set the starting balance
        System.out.println("Danny has a starting balance of "+startingBalance);
        danny.setAccountBalance(startingBalance,"EUR");
    }

    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double newTopUp) {
        //set the top up
        this.topUpAmount = newTopUp;
    }

    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double newBalanceInAccount) {
        // add funds to the euro account and see response
        danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod.getResponseFromService());
    }

    @Given("Peter has {double} Euro in credits")
    public void peter_has_euro_in_credits(double startingBalance) {
        // set the starting balance for peter's account
        peter.setAccountBalance(startingBalance,"EUR");
    }
    @When("Peter purchases a product from {string}")
    public void peter_purchases_a_product_from(String vendorName) {
        // Check if the vendor is and affiliated one
        firstVendor.isVendorAnAffiliate(vendorName);
    }
    @When("Peter pays with credits amount {double}")
    public void peter_pays_with_credits(double amount) {
        // pay with credit for cashback
        double newAmount = peter.getAccountBalance("EUR") - amount;
        peter.getAccount("EUR").setBalance(newAmount);

    }
    @Then("Peter gets a discount of {double} cashback")
    public void peter_gets_a_discount_of_cashback(Double cashback) {

        //Arrange
        double currentBalance = peter.getAccountBalance("EUR");
        double newBalance = currentBalance * (cashback+1);
        double expectedResult = currentBalance * (cashback+1);
        //Act
        peter.getAccount("EUR").setBalance(newBalance);
        double actualResult = peter.getAccountBalance("EUR");;
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance after cashback is: " + actualResult);
    }

    @And("Danny has {word} in his topUp source")
    public void dannyHasFundsInHisTopUpSource(String topUpResponse) {
        System.out.println("The topUp response from service  was " + topUpResponse);
        //Set the payment response
        topUpMethod.setResponseFromService(topUpResponse);
    }

    @Given("Peter has {double} euro in his euro Revolut account")
    public void peter_has_euro_in_his_euro_revolut_account(double startingBalance) {
        // set the starting balance for peter
        System.out.println("Peter has a starting balance of "+startingBalance);
        peter.setAccountBalance(startingBalance,"EUR");
    }
    @Given("Peter selects {double} as the exchange amount for {word}")
    public void peter_selects_sterling_as_the_exchange_amount(double exchangeAmount, String currency) {
        // set up a gbp account and set the amount for exchange
        this.currency = currency;
        peter.addAccount(currency);
        this.exchangeAmount = peter.getCalculateCurrency("EUR", exchangeAmount, currency);

    }
    @Given("Peter selects his {paymentService} as his exchange method")
    public void peter_selects_his_revolut_account_as_his_exchange_method(PaymentService source) {
        // Set the payment service
        System.out.println("The selected payment service type was " + source.getType());
        bankAccount = source;
    }
    @Given("Peter has {word} in his exchange source")
    public void peter_has_sufficient_funds_in_his_exchange_source(String response) {
        // check if peter has sufficient funds to exchange
        System.out.println("The response from service  was " + response);
        //Set the response
        bankAccount.setResponseFromService(response);
    }
    @When("Peter exchanges money")
    public void peter_exchange_money() {
        // calculate the exchange add funds to GBP account. Set the balance of euro account
        double calculateExchangeAmount = this.exchangeAmount;//peter.getCalculateCurrency("EUR", exchangeAmount, "GBP");
        double currentBalance = peter.getAccountBalance("EUR");
        double newBalance = currentBalance - calculateExchangeAmount;
        peter.setAccountBalance(newBalance, "EUR");
        peter.getAccount(this.currency).addFunds(exchangeAmount, bankAccount.getResponseFromService());

    }
    @Then("The new balance of his forex {word} account should now be {double}")
    public void the_new_balance_of_his_sterling_account_should_now_be(String currency, double updatedBalance) {
        //Act
        double expectedResult = peter.getAccountBalance(currency);
        //Assert
        Assert.assertEquals(expectedResult, updatedBalance,0);
        System.out.println("The new balance in "+ currency +" Account is: " + expectedResult);
    }

    @When("Peter selects a {word} from revolut contacts")
    public void peter_selects_a_friend_from_revolut_contacts(String friend) {
        // Search for contact in via revolut contacts
        if(friend == peter.getContact("Tim"))
            contact.addAccount("EUR");
    }
    @When("Peter selects {double} euro to send")
    public void peter_selects_euro_to_send(double amount) {
        // Select the amount to send
        this.sendToFriendAmount = amount;

    }
    @When("Peter selects his {paymentService} as his transfer method")
    public void peter_selects_his_revolut_premium_account_as_his_transfer_method(PaymentService source) {
        // Select the payment service
        System.out.println("The selected transfer to contact service type was " + source.getType());
        bankAccount = source;

    }
    @When("Peter has {word} in his Bank Account")
    public void peter_has_sufficient_funds_in_his_bank_account(String response) {
        // Does the person have sufficient funds
        System.out.println("The response from service  was " + response);
        bankAccount.setResponseFromService(response);
    }
    @When("Peter transfers money")
    public void peter_transfers_money() {
        // send money to a friend
        double currentBalance = peter.getAccountBalance("EUR");
        double newBalance = currentBalance - sendToFriendAmount;
        peter.setAccountBalance(newBalance, "EUR");
    }
    @Then("Peters account balance should be {double} Euro")
    public void peter_has_new_account_balance(double newBalance) {
        // Test the new account balance
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = peter.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance after contact transfer is: " + actualResult);
    }

}
