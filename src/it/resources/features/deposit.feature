Feature: deposit feature

  Scenario: client deposit amount to account

    When the client initiate a DEPOSIT transaction with the following information
      | amount | accountId |
      | 10     | 1         |
    Then the client receives the transaction info as bellow
      | amount | transactionType |
      | 10     | DEPOSIT         |
