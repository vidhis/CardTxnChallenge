package com.vidhi.cardtest.carddetails;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	long amount;
	
	 @JsonProperty("is-pending")
    boolean isPending;
	
	 @JsonProperty("aggregation-time")
    long aggregationTime;
	
	@JsonProperty("account-id")
    String accountID;
	
	@JsonProperty("clear-date")
    long clearDate;
	
	@JsonProperty("transaction-id")
    String transactionID;
    
	@JsonProperty("raw-merchant")
	String rawMerchant;
	
    String categorization;
    String merchant;
    
    @JsonProperty("transaction-time")
    String transactionTime;
    
    @JsonProperty("previous-transaction-id")
    String previousTransactionID;
    //String memo_only_for_testing;
    //String payee_name_only_for_testing;
    
    @JsonProperty("memo-only-for-testing")
    String memoOnlyForTesting;
 
    @JsonProperty("payee-name-only-for-testing")
    String payeeNameOnlyForTesting;
 
    
    public long getAmount() {
		return amount;
	}
    
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public String getAccountID() {
		return accountID;
	}
    
	public void setAccountID(String accountId) {
		this.accountID = accountID;
	}
    
	public boolean getIsPending() {
		return isPending;
	}
    
	public void setIsPending(boolean isPending) {
		this.isPending = isPending;
	}
	
	 public long getAggregationTime() {
			return aggregationTime;
	}
	    
	public void setAggregationTime(long aggregationTime) {
			this.aggregationTime = aggregationTime;
	}
	
	 public long getClearDate() {
			return clearDate;
	}
	    
	public void setClearDate(long clearDate) {
			this.clearDate = clearDate;
	}
	
	public String getTransactionID() {
		return transactionID;
	}
    
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	public String getRawMerchant() {
		return rawMerchant;
	}
    
	public void setRawMerchant(String rawMerchant) {
		this.rawMerchant = rawMerchant;
	}
	
	public String getCategorization() {
		return categorization;
	}
    
	public void setCategorization(String categorization) {
		this.categorization = categorization;
	}
	
	public String getMerchant() {
		return merchant;
	}
    
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	
	public String getTransactionTime() {
		return transactionTime;
	}
    
	public void setTransactionTime(String transaction_time) {
		this.transactionTime = transactionTime;
	}
	
	
	public String getPreviousTransactionID() {
		return previousTransactionID;
	}
    
	public void setPreviousTransactionID(String previousTransactionID) {
		this.previousTransactionID = previousTransactionID;
	}
	
	public String getPayeeNameOnlyForTesting() {
		return payeeNameOnlyForTesting;
	}
    
	public void setPayeeNameOnlyForTesting(String payeeNameOnlyForTesting) {
		this.payeeNameOnlyForTesting = payeeNameOnlyForTesting;
	}
	
	public String getMemoOnlyForTesting() {
		return memoOnlyForTesting;
	}
    
	public void setMemoOnlyForTesting(String memoOnlyForTesting) {
		this.memoOnlyForTesting = memoOnlyForTesting;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***** Transaction Details *****\n");
		sb.append("Account ID="+getAccountID()+"\n");
		sb.append("Transaction ID="+getTransactionID()+"\n");
		sb.append("Previous Transaction ID="+getPreviousTransactionID()+"\n");
		sb.append("Raw Merchant="+getRawMerchant()+"\n");
		sb.append("Merchant="+getMerchant()+"\n");
		sb.append("Transaction Time="+getTransactionTime()+"\n");
		sb.append("Amount="+getAmount()+"\n");
		sb.append("Is Pending="+getIsPending()+"\n");
		sb.append("Clear Date="+getClearDate()+"\n");
		sb.append("Categorization="+getCategorization()+"\n");
		sb.append("Aggregation Time="+getAggregationTime()+"\n");
		sb.append("Memo for Testing="+getMemoOnlyForTesting()+"\n");
		sb.append("Payee Name for Testing="+getPayeeNameOnlyForTesting()+"\n");
		sb.append("*****************************");
		
		return sb.toString();
	}
}
