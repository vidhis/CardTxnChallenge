package com.vidhi.cardtest.carddetails;


//Class for the Output: Transaction Month and  Amount Spent and Income
public class OutputObj {

	private String yyyymm;
	private long amtSpent;
	private long amtEarned;
	
	public void setYyyyMm(String yyyymm){
		this.yyyymm=yyyymm;
	}
	
	public String getYyyyMm(){
		return yyyymm;
	}
	
	public void setAmtSpent(long amtSpent){
		this.amtSpent=amtSpent;
	}
	
	public long getAmtSpent(){
		return amtSpent;
	}
	
	public void setAmtEarned(long amtEarned){
		this.amtEarned=amtEarned;
	}
	
	public long getAmtEarned(){
		return amtEarned;
	}
	
	//Method to get Output string in expected format
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getYyyyMm()+": { spent: $"+ Math.abs(getAmtSpent())+", income: $"+getAmtEarned()+"}");
		return sb.toString();
	}
}
