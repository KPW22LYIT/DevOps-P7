Feature: Premium Account Cash Back
  This feature describes various scenarios for users using a premium account card on purchases and earning cashback

  # As a user, I can purchase goods at an affiliated vendor
  # using my Revolut card a purchase should earn cashback

 #The scenarios below for preforming a currency exchange
 Scenario: Premium account purchase at affiliated vendor
   Given Peter has 20 Euro in credits
   When Peter purchases a product from "affiliated vendor"
    And Peter pays with credits amount 10
   Then Peter gets a discount of 0.003 cashback

   # As a user, I can exchange currency using my Revolut Premium account
   # The scenarios below for preforming a currency exchange
  Scenario Outline: Preforming a currency exchange
    Given Peter has <startBalance> euro in his euro Revolut account
    And Peter selects <exchangeAmount> as the exchange amount for <currency>
    And  Peter selects his RevolutPremiumAccount as his exchange method
    And  Peter has sufficientFunds in his exchange source
    When Peter exchanges money
    Then The new balance of his forex <currency> account should now be <newBalance>
    Examples:
      | startBalance | exchangeAmount | currency |newBalance|
      | 600          | 100            | GBP      | 88       |
      | 120          | 20             | USD      | 24       |
      | 34           | 30             | JPY      | 4018.2   |

    # As a user, I can send money to a revolut contact using my Premium account
    # The scenarios below for sending money to a friend using Revolut
  Scenario: Sending money to a friend
    Given Peter has 100 euro in his euro Revolut account
    When Peter selects a Tim from revolut contacts
    And  Peter selects 25 euro to send
    And  Peter selects his RevolutPremiumAccount as his transfer method
    And  Peter has sufficientFunds in his Bank Account
    When Peter transfers money
    Then Peters account balance should be 75 Euro