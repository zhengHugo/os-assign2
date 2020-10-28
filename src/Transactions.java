/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Transactions class
 *
 * @author Kerly Titus
 */
public class Transactions {

  private String accountNumber; /* Account number */
  private String operationType; /* Operation type : deposit, withdrawal, query */
  private double transactionAmount; /* Amount to deposit or withdraw */
  private double transactionBalance; /* Account balance after transaction */
  /* Transaction error : NSF, invalid amount, invalid account, none */
  private String transactionError;
  /* Current transaction status : pending, sent, received, transferred, done */
  private String transactionStatus;

  /**
   * Constructor method of Transactions class
   */
  Transactions() {
    accountNumber = " ";
    operationType = " ";
    transactionAmount = 0.00;
    transactionBalance = 0.00;
    transactionError = "none";
    transactionStatus = " ";
  }

  /**
   * Accessor method of Transactions class
   *
   * @return accountNumber
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param accNumber
   */
  public void setAccountNumber(String accNumber) {
    accountNumber = accNumber;
  }

  /**
   * Accessor method of Transactions class
   *
   * @return operationType
   */
  public String getOperationType() {
    return operationType;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param opType
   */
  public void setOperationType(String opType) {
    operationType = opType;
  }

  /**
   * Accessor method of Transactions class
   *
   * @return transactionAmount
   */
  public double getTransactionAmount() {
    return transactionAmount;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param transAmount
   */
  public void setTransactionAmount(double transAmount) {
    transactionAmount = transAmount;
  }

  /**
   * Accessor method of Transactions class
   *
   * @return transactionBalance
   */
  public double getTransactionBalance() {
    return transactionBalance;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param transBalance
   */
  public void setTransactionBalance(double transBalance) {
    transactionBalance = transBalance;
  }

  /**
   * Accessor method of Transactions class
   *
   * @return transactionError
   */
  public String getTransactionError() {
    return transactionError;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param transError
   */
  public void setTransactionError(String transError) {
    transactionError = transError;
  }

  /**
   * Accessor method of Transactions class
   *
   * @return transactionStatus
   */
  public String getTransactionStatus() {
    return transactionStatus;
  }

  /**
   * Mutator method of Transactions class
   *
   * @param transStatus
   */
  public void setTransactionStatus(String transStatus) {
    transactionError = transStatus;
  }

  /**
   * Create a String representation based on the Transactions Object
   *
   * @return String representation
   */
  public String toString() {
    return ("\n Account number "
        + getAccountNumber()
        + " Account Balance "
        + getTransactionBalance()
        + " Message "
        + getTransactionError());
  }
}
