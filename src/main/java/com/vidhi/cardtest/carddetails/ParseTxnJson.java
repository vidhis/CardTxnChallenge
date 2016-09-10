package com.vidhi.cardtest.carddetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ForwardingTable;
import com.vidhi.cardtest.carddetails.Transaction;

public class ParseTxnJson {

/*	public static void main(String[] args) throws IOException {
		
		ArrayList<Transaction> a1 = new ArrayList<Transaction>();
		//Iterator i = a1.iterator();
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		Transaction t1 = objectMapper.readValue(jsonData, Transaction.class);
		//if((!t1.getMerchant().contains("DUNKIN #336784")) && (!t1.getMerchant().contains("Krispy Kreme Donuts")) )
		a1.add(t1);
		
		for(Transaction t4:a1){
		System.out.println("Transaction Object\n"+t4);
		}
		
		//convert Object to json string
		Transaction t2 = createTxn();
		//configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//writing to console, can write to any output stream such as file
		StringWriter stringTxn = new StringWriter();
		objectMapper.writeValue(stringTxn, t2);
	//	System.out.println("Transaction JSON is\n"+stringTxn);
		
		FileWriter fw = new FileWriter("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions1.txt");
		fw.write(stringTxn.toString());
		fw.close();
		
		LoadJson.bringJson();
	}*/
	
	public static void main(String[] args) throws IOException {
		
		
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		TxnHistory t1 = objectMapper.readValue(jsonData, TxnHistory.class);
		//if((!t1.getMerchant().contains("DUNKIN #336784")) && (!t1.getMerchant().contains("Krispy Kreme Donuts")) )
		System.out.println("Size of array of transactions is " +t1.getTransactions().length);
		
		Transaction[] arr= new Transaction[t1.getTransactions().length];
		
		for(int cnt=0;cnt<t1.getTransactions().length;cnt++){
		arr[cnt] = new Transaction();	
		arr[cnt] =t1.getTransactions()[cnt];
//		System.out.println(arr[cnt]);
		}

		Map<String, Long> m1 = new HashMap<String, Long>();
		
		for(int k=0; k<arr.length;k++){
						
			if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)))
				m1.put(arr[k].getTransactionTime().substring(0, 7), m1.get(arr[k].getTransactionTime().substring(0, 7))+arr[k].getAmount());
			
			else
				m1.put(arr[k].getTransactionTime().substring(0, 7),arr[k].getAmount());
		}
		
		Set s = m1.entrySet();
		Iterator it1 = s.iterator();
		
		while(it1.hasNext()){
			Map.Entry me = (Map.Entry)it1.next();
			System.out.println("Key :"+me.getKey()+" Value: "+me.getValue());	
		}
		
		//convert Object to json string
		TxnHistory t2 = createTxnHistory();
		//configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//writing to console, can write to any output stream such as file
		StringWriter stringTxn = new StringWriter();
		objectMapper.writeValue(stringTxn, t2);
	//	System.out.println("Transaction JSON is\n"+stringTxn);
		
		FileWriter fw = new FileWriter("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions1.txt");
		fw.write(stringTxn.toString());
		fw.close();
		
		LoadJson.bringJson();
	}
	
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
		t3.setAmount(3454999993L);
		t3.setCategorization("Unknown");
		t3.setClearDate(1412985120000L);
		t3.setMerchant("Sunoco");
		t3.setPreviousTransactionID("234234345345");
		t3.setRawMerchant("SUNOCO 0299792200");
		t3.setTransactionID("1412985120000");
		t3.setTransactionTime("014-10-07T17:29:00.000Z");
		
		return t3;
	}
	
}