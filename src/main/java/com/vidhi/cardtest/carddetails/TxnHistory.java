package com.vidhi.cardtest.carddetails;

//Class with definition of records coming in API Response
public class TxnHistory {

	private String error;
	private Transaction[] transactions;
	
	public void setError(String error){
		this.error = error;
	}
	
	public String getError(){
		return error;
	}
	
	public void setTransactions(Transaction[] transactions){
		this.transactions = transactions;
	}
	
	public Transaction[] getTransactions(){
		return transactions;
	}
	
	
}
