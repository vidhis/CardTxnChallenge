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
		String spentStr, incomeStr;
		StringBuilder sb = new StringBuilder();
		spentStr = Long.toString(Math.abs(getAmtSpent()));
		int spentdollar = spentStr.length()-2;
		incomeStr=Long.toString(getAmtEarned());
		int incomedollar= incomeStr.length()-2;
		
		//System.out.println(spentStr+ ","+ spentdollar+ ","+ incomeStr+ ","+incomedollar);
		
		sb.append(getYyyyMm()+": { spent: $"+ spentStr.substring(0,spentdollar)+"."+spentStr.substring(spentdollar) +","
				+ " income: $"+incomeStr.substring(0,incomedollar)+"."+incomeStr.substring(incomedollar)+"}");
		return sb.toString();
	}
}
