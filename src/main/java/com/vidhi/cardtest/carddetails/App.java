package com.vidhi.cardtest.carddetails;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class App {
public static void main(String[] args) throws IOException {
		
		ParseTxnJson.loadAllData();
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		TxnHistory t1 = objectMapper.readValue(jsonData, TxnHistory.class);
		//if((!t1.getMerchant().contains("DUNKIN #336784")) && (!t1.getMerchant().contains("Krispy Kreme Donuts")) )
		System.out.println("Size of array of transactions is " +t1.getTransactions().length);
		
		
		//CalcValuesMapArrayList(arr);
		ArrayList<OutputObj> ao=ParseTxnJson.CalcValNoArrayList(t1.getTransactions());
		FileWriter fw1 = new FileWriter("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/final.txt");
		
		for(int p=0;p<ao.size()-1;p++){
		fw1.write(ao.get(p).toString());
		}
		
		fw1.close();
		
		//CalcValuesMapArrayList(arr);
				ArrayList<OutputObj> ao1=ParseTxnJson.CalcValNoDonut(t1.getTransactions());
				FileWriter fw2 = new FileWriter("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/NoDonut.txt");
				
				for(int r=0;r<ao1.size()-1;r++){
				fw2.write(ao1.get(r).toString());
				}
				
				fw2.close();
		
		
		//convert Object to json string
		TxnHistory t2 = ParseTxnJson.createTxnHistory();
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
	
}
	
	
	
	
	
