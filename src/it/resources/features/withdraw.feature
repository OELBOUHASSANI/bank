Feature: withdraw feature

  Scenario: client withdraw amount to account

    When the client initiate a WITHDRAW transaction with the following information
      | amount | accountId |
      | 100    | 2         |
    Then the client receives the transaction info as bellow
      | amount | transactionType |
      | 100    | WITHDRAW        |
