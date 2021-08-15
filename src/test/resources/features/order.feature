Feature: Create Order

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a order with the following attributes
      | orderId  | billNo  | orderDate         | orderAmount       | taxAmount      | billingAmount   | PaymentMode   |currencyType   | customerId       | storeId |
    	| 1  			 | 100     | 2021-03-20       | 10000          | 300         | 9000    		    | cash          |INR            | 302              | 678     |
    When admin saves the new order 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
