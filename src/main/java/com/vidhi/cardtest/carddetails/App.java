package com.vidhi.cardtest.carddetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class App {
	
	public static void main(String[] args) throws IOException {
		
		//find current directory
		Path currentRelativePath = Paths.get("");
		String currentDir = currentRelativePath.toAbsolutePath().toString();
				
		
		//Loads API Response into  File
		LoadJson.loadAllData(currentDir);
		
		File f = new File(currentDir+"/src/main/java/com/vidhi/cardtest/carddetails/AllSpendingIncome.txt");
		if(f.exists())
			f.delete();
		
		File f1 = new File(currentDir+"/src/main/java/com/vidhi/cardtest/carddetails/NoDonut.txt");
		if(f1.exists())
			f1.delete();
		
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get(currentDir+"/src/main/java/com/vidhi/cardtest/carddetails/Transactions.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		TxnHistory t1 = objectMapper.readValue(jsonData, TxnHistory.class);
		//System.out.println("Size of array of transactions is " +t1.getTransactions().length);
		
		
		//If no arguments are passed while runnning from command prompt - all Transactions will be considered to find Income and Spent
		if(args.length==0){
		ArrayList<OutputObj> ao=ParseTxnJson.CalcValNoArrayList(t1.getTransactions());		
		FileWriter fw1 = new FileWriter(currentDir+"/src/main/java/com/vidhi/cardtest/carddetails/AllSpendingIncome.txt");
		
		for(int p=0;p<ao.size();p++){
		fw1.write(ao.get(p).toString()+"\n");
		}
		
		fw1.close();
		
		for(int p=0;p<ao.size();p++){
			System.out.println(ao.get(p).toString());
		}
		
		}
		
		//When an argument is passed to the Program and it is ignore-donuts then all txns which have DUNKIN #336784 or 
		//Krispy Kreme Donuts are ignored from the Spent amount calculation
		if(args.length==1 && args[0].equalsIgnoreCase("ignore-donuts")){
				
			ArrayList<OutputObj> ao1=ParseTxnJson.CalcValNoDonut(t1.getTransactions());
			FileWriter fw2 = new FileWriter(currentDir+"/src/main/java/com/vidhi/cardtest/carddetails/NoDonut.txt");
				
			for(int r=0;r<ao1.size();r++){
				fw2.write(ao1.get(r).toString()+"\n");
			}
				
			fw2.close();
			
			for(int p=0;p<ao1.size();p++){
				System.out.println(ao1.get(p).toString());
			}
		}		
		
		//If argument other than ignore-donuts is passed then build will succeed but nothing will happen
		if(args.length==1 && !(args[0].equalsIgnoreCase("ignore-donuts"))){	
				System.out.println("Not a valid option..exiting");
			
		}	
	}
	
}
	
	
	
	
	
