Feature: Find Order

  Scenario: FINDING ORDER BY ID

    Given The following order exists and Admin wants to find the order by ID
      | orderId  | billNo  | orderDate         | orderAmount       | taxAmount              | billingAmount   | PaymentMode   |currencyType   | customerId       | storeId |
    	| 1  			 | 100     | 2021-02-20       | 10000          | 300                | 9000    		    | cash          |INR            | 302              | 678     |
      | 2  			 | 101     | 2021-03-20       | 13000          | 330                | 11000    		    | cash          |INR            | 304              | 678     |
    When admin wants to find the order with ID '1'
    Then the order is found
