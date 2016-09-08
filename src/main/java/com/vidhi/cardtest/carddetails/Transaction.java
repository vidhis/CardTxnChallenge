package com.vidhi.cardtest.carddetails;

import java.math.BigInteger;

public class Transaction {

	BigInteger amount;
    boolean is_pending;
   // "aggregation-time": 1412686740000,
    String account_id;
    BigInteger clear_date;
    String transaction_id;
    String raw_merchant;
    String categorization;
    String merchant;
    String transaction_time;
    String previous_transaction_id;
    //String memo_only_for_testing;
    //String payee_name_only_for_testing;
    
    public BigInteger getAmount() {
		return amount;
	}
    
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	
	public String getAccountID() {
		return account_id;
	}
    
	public void setAccountID(String account_id) {
		this.account_id = account_id;
	}
    
	public boolean getIsPending() {
		return is_pending;
	}
    
	public void setAccountID(boolean is_pending) {
		this.is_pending = is_pending;
	}
	
	 public BigInteger getClearDate() {
			return clear_date;
	}
	    
	public void setClearDate(BigInteger clear_date) {
			this.clear_date = clear_date;
	}
	
	public String getTransactionID() {
		return transaction_id;
	}
    
	public void setTransactionID(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getRawMerchant() {
		return raw_merchant;
	}
    
	public void setRawMerchant(String raw_merchant) {
		this.raw_merchant = raw_merchant;
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
		return transaction_time;
	}
    
	public void setTransactionTime(String transaction_time) {
		this.transaction_time = transaction_time;
	}
	
	
	public String getPreviousTransactionID() {
		return previous_transaction_id;
	}
    
	public void setPreviousTransactionID(String previous_transaction_id) {
		this.previous_transaction_id = previous_transaction_id;
	}
	
	
}
