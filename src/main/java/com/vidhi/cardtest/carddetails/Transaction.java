package com.vidhi.cardtest.carddetails;

import java.math.BigInteger;

public class Transaction {

	long amount;
    String isPending;
   // "aggregation-time": 1412686740000,
    String accountID;
    long clearDate;
    String transactionID;
    String rawMerchant;
    String categorization;
    String merchant;
    String transactionTime;
    String previousTransactionID;
    //String memo_only_for_testing;
    //String payee_name_only_for_testing;
    
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
    
	public String getIsPending() {
		return isPending;
	}
    
	public void setIsPending(String isPending) {
		this.isPending = isPending;
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
		sb.append("*****************************");
		
		return sb.toString();
	}
}
