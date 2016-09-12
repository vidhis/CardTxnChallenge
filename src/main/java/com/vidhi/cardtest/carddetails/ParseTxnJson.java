package com.vidhi.cardtest.carddetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ForwardingTable;
import com.vidhi.cardtest.carddetails.Transaction;

public class ParseTxnJson {

	
	
	public static TxnHistory createTxnHistory(){
		
		TxnHistory t5 = new TxnHistory();
		Transaction[] t6= {createTxn(), createTxn()};
		
		t5.setError("no error Vidhi yaay");
		t5.setTransactions(t6);
		
		return t5;
		
	}
	
	public static Transaction createTxn() {

		Transaction t3= new Transaction();
		
		t3.setIsPending(true);
		t3.setAggregationTime(1412456120000L);
		t3.setAccountID("12353454");
		t3.setAmount(345499);
		t3.setCategorization("Unknown");
		t3.setClearDate(1412985120000L);
		t3.setMerchant("Sunoco");
		t3.setPreviousTransactionID("234234345345");
		t3.setRawMerchant("SUNOCO 0299792200");
		t3.setTransactionID("1412985120000");
		t3.setTransactionTime("014-10-07T17:29:00.000Z");
		
		return t3;
	}

	
	public static ArrayList<OutputObj> CalcValNoArrayList(Transaction[] arr1){
		
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
		System.out.println(avgSpent);
		avgIncome = incomeTotal/noOfMonths;
		System.out.println(avgIncome);
		
		OutputObj ob3=new OutputObj();
		ob3.setYyyyMm("Average");
		ob3.setAmtEarned(avgIncome);
		ob3.setAmtSpent(avgSpent);
		aObj.add(ob3);
		
		for(int p=0;p<aObj.size();p++){
			System.out.println(aObj.get(p).toString());
			}
		
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
		System.out.println(avgSpent);
		avgIncome = incomeTotal/noOfMonths;
		System.out.println(avgIncome);

		OutputObj ob3=new OutputObj();
		ob3.setYyyyMm("Average");
		ob3.setAmtEarned(avgIncome);
		ob3.setAmtSpent(avgSpent);
		aObj.add(ob3);
		
		
		for(int p=0;p<aObj.size();p++){
			System.out.println(aObj.get(p).toString());
		}
			
		
		return aObj;
	}

	
			
}