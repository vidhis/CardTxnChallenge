package com.vidhi.cardtest.carddetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadJson {
	
	public static void bringJson(){
		
		String token = "\"9B006AA9FC0ACA764A2CDC6E29CF32DF\",";
		String apitoken = "\"AppTokenForInterview\",";
		String uid = "1110590645,";		
		String url = "https://prod-api.level-labs.com/api/v2/core/get-all-transactions";		
		//String filepath = "/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions2.txt";
		String filepath = "TxnData.txt";
		
//		String[] command1 = {"/usr/bin/curl", "-H" ,"'Accept: application/json'", "-H","'Content-Type: application/json'",
//		    		  "-X", "POST", "-d","'{\"args\":", "{\"uid\":", uid,"\"token\":",token,
//		    		  "\"api-token\":",apitoken,"\"json-strict-mode\":","false,", "\"json-verbose-response\":","false}}'",
//		    		   url, ">>",filepath};
//		
//		String command1 = "/usr/bin/curl -H 'Accept: application/json' -H 'Content-Type: application/json' -X POST -d '{\"args\": {\"uid\": 1110590645, \"token\": \"9B006AA9FC0ACA764A2CDC6E29CF32DF\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}' https://prod-api.level-labs.com/api/v2/core/get-all-transactions >> /Users/Vidhi/Documents/curloutput.txt";
//		
//		try {
//			Process p1 = Runtime.getRuntime().exec(command1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		ProcessBuilder process = new ProcessBuilder("/usr/bin/curl", "-H" ,"'Accept: application/json'", "-H","'Content-Type: application/json'",
  		  "-X", "POST", "-d","'{\"args\":", "{\"uid\":", uid,"\"token\":",token,
  		  "\"api-token\":",apitoken,"\"json-strict-mode\":","false,", "\"json-verbose-response\":","false}}'",
  		   url,">>","Vidhi.txt"); 
		Process p;
		        try
		        {
		        	process.redirectOutput(new File("curloutput.txt"));
		            p = process.start();

		            System.out.println(process.command());
		            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
		                StringBuilder builder = new StringBuilder();
		                String line = null;
		                while ( (line = reader.readLine()) != null) {
		                        builder.append(line);
		                        builder.append(System.getProperty("line.separator"));
		                }
		                String result = builder.toString();
		                System.out.print(result);

		        }
		        catch (IOException e)
		        {   System.out.print("error");
		            e.printStackTrace();
		        }
		        
	}

}
