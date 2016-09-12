package com.vidhi.cardtest.carddetails;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ParseTxnJson {
	
	public static ArrayList<OutputObj> CalcValNoArrayList(Transaction[] arr1){
		
		//Map to hold each type of Txn - Spent and Income by month
		Map<String, Long> m1 = new HashMap<String, Long>();
		long tempSpent=0;
		
		Transaction[] arr= new Transaction[arr1.length];
		
		for(int cnt=0;cnt<arr1.length;cnt++){
		arr[cnt] = new Transaction();	
		arr[cnt] =arr1[cnt];
		//System.out.println(arr[cnt]);
		}
		
		//Calculates Spent which is -ve amount
		for(int k=0; k<arr.length;k++){
						
			if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()<0)){
			//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
				tempSpent = m1.get(arr[k].getTransactionTime().substring(0, 7))+arr[k].getAmount();
				m1.remove(arr[k].getTransactionTime().substring(0, 7));
				m1.put(arr[k].getTransactionTime().substring(0, 7), tempSpent);
			}
			
			else if(!(m1.containsKey(arr[k].getTransactionTime().substring(0, 7))) && (arr[k].getAmount()<0)){	
			//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
				m1.put(arr[k].getTransactionTime().substring(0, 7),arr[k].getAmount());
			}

		}
		
		
		ArrayList<OutputObj> aObj = new ArrayList<OutputObj>();
		
		//Creating new objects with Month details and Spent amount, adding them to Array
		for (Map.Entry<String, Long> entry : m1.entrySet()) {
			OutputObj ob1=new OutputObj();
			ob1.setYyyyMm(entry.getKey());
		   ob1.setAmtSpent(entry.getValue());
		   aObj.add(ob1);
		}
		
		m1.clear();
		
		//Calculates Income which is +ve amount
				for(int k=0; k<arr.length;k++){
								
					if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()>0)){
					//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
						tempSpent = m1.get(arr[k].getTransactionTime().substring(0, 7))+arr[k].getAmount();
						m1.remove(arr[k].getTransactionTime().substring(0, 7));
						m1.put(arr[k].getTransactionTime().substring(0, 7), tempSpent);
					}
					
					else if(!(m1.containsKey(arr[k].getTransactionTime().substring(0, 7))) && (arr[k].getAmount()>0)){	
					//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
						m1.put(arr[k].getTransactionTime().substring(0, 7),arr[k].getAmount());
					}

				}
		
				boolean flagMonFound=false;
				
				//Checks existing objects with Month details and updates income to them
				for (Map.Entry<String, Long> entry1 : m1.entrySet()) {				
					for(int q=0;q<aObj.size();q++){		
						if(aObj.get(q).getYyyyMm().matches(entry1.getKey())){
							aObj.get(q).setAmtEarned(entry1.getValue());
						}
					}
				}
				
		
		//Logic to calculate Average	
		int noOfMonths = aObj.size();
		long spentTotal =0L;
		long incomeTotal =0L;
		long avgSpent=0L;
		long avgIncome=0L;
		
		for(int p=0;p<aObj.size();p++){
			spentTotal+=aObj.get(p).getAmtSpent();
			incomeTotal+=aObj.get(p).getAmtEarned();
		}
		
		avgSpent = spentTotal/noOfMonths;
		avgIncome = incomeTotal/noOfMonths;
		
		OutputObj ob3=new OutputObj();
		ob3.setYyyyMm("average");
		ob3.setAmtEarned(avgIncome);
		ob3.setAmtSpent(avgSpent);
		aObj.add(ob3);
		
		return aObj;
	}
			
	public static ArrayList<OutputObj> CalcValNoDonut(Transaction[] arr1){
		
		Map<String, Long> m1 = new HashMap<String, Long>();
		long tempSpent=0;
		
		Transaction[] arr= new Transaction[arr1.length];
		
		for(int cnt=0;cnt<arr1.length;cnt++){
		arr[cnt] = new Transaction();	
		arr[cnt] =arr1[cnt];
		//System.out.println(arr[cnt]);
		}
		
		//Calculates Spent which is -ve amount
		for(int k=0; k<arr.length;k++){

				
			if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()<0) && (!arr[k].getMerchant().equalsIgnoreCase("DUNKIN #336784")) && (!arr[k].getMerchant().equalsIgnoreCase("Krispy Kreme Donuts"))  ){
			//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
				tempSpent = m1.get(arr[k].getTransactionTime().substring(0, 7))+arr[k].getAmount();
				m1.remove(arr[k].getTransactionTime().substring(0, 7));
				m1.put(arr[k].getTransactionTime().substring(0, 7), tempSpent);
			}
			
			else if(!(m1.containsKey(arr[k].getTransactionTime().substring(0, 7))) && (arr[k].getAmount()<0)){	
			//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
				m1.put(arr[k].getTransactionTime().substring(0, 7),arr[k].getAmount());
			}

		}
		
		
		ArrayList<OutputObj> aObj = new ArrayList<OutputObj>();
		
		for (Map.Entry<String, Long> entry : m1.entrySet()) {
			OutputObj ob1=new OutputObj();
			ob1.setYyyyMm(entry.getKey());
		   ob1.setAmtSpent(entry.getValue());
		   aObj.add(ob1);
		}
		
		m1.clear();
		
		//Calculates Income which is +ve amount
				for(int k=0; k<arr.length;k++){
								
					if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()>0)){
					//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
						tempSpent = m1.get(arr[k].getTransactionTime().substring(0, 7))+arr[k].getAmount();
						m1.remove(arr[k].getTransactionTime().substring(0, 7));
						m1.put(arr[k].getTransactionTime().substring(0, 7), tempSpent);
					}
					
					else if(!(m1.containsKey(arr[k].getTransactionTime().substring(0, 7))) && (arr[k].getAmount()>0)){	
					//	System.out.println(arr[k].getTransactionTime().substring(0, 7));			
						m1.put(arr[k].getTransactionTime().substring(0, 7),arr[k].getAmount());
					}

				}
		
				boolean flagMonFound=false;
				
				for (Map.Entry<String, Long> entry1 : m1.entrySet()) {				
					for(int q=0;q<aObj.size();q++){		
						if(aObj.get(q).getYyyyMm().matches(entry1.getKey())){
							aObj.get(q).setAmtEarned(entry1.getValue());
						}
					}
				}
		
		//Logic to calculate Average	
		int noOfMonths = aObj.size();
		long spentTotal =0L;
		long incomeTotal =0L;
		long avgSpent=0L;
		long avgIncome=0L;
		
		for(int p=0;p<aObj.size();p++){
			spentTotal+=aObj.get(p).getAmtSpent();
			incomeTotal+=aObj.get(p).getAmtEarned();
		}
		
		avgSpent = spentTotal/noOfMonths;
		avgIncome = incomeTotal/noOfMonths;

		OutputObj ob3=new OutputObj();
		ob3.setYyyyMm("average");
		ob3.setAmtEarned(avgIncome);
		ob3.setAmtSpent(avgSpent);
		aObj.add(ob3);
		
		return aObj;
	}

	
			
}