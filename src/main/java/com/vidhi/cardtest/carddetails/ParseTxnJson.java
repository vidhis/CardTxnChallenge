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
	
	public static void CalcValuesMapArrayList(Transaction[] arr){
		
		Map<String, ArrayList<Long>> m1 = new HashMap<String, ArrayList<Long>>();
		ArrayList<Long> al= new ArrayList<Long>();
		long tempSpent=0;
		
		//Calculates Spent which is -ve amount
		for(int k=0; k<arr.length;k++){
						
			if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()<0)){
			
				//	System.out.println(arr[k].getAmount()+" "+m1.get(arr[k].getTransactionTime().substring(0, 7)));
				
				tempSpent = (m1.get(arr[k].getTransactionTime().substring(0, 7))).get(0)+arr[k].getAmount();
				(m1.get(arr[k].getTransactionTime().substring(0, 7))).remove(0);
				(m1.get(arr[k].getTransactionTime().substring(0, 7))).add(0, tempSpent);
				//m1.put(arr[k].getTransactionTime().substring(0, 7), al);
			}
			
			else if(!m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()<0)){
				//(m1.get(arr[k].getTransactionTime().substring(0, 7))).add((0, arr[k].getAmount());
				al.add(0, arr[k].getAmount());
				m1.put(arr[k].getTransactionTime().substring(0, 7),al);
			}

		}
		
		//Calculates Income which is +ve amount
		for(int k=0; k<arr.length;k++){
			
			if(m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()>0)){
			
				//	System.out.println(arr[k].getAmount()+" "+m1.get(arr[k].getTransactionTime().substring(0, 7)));
				
				tempSpent = (m1.get(arr[k].getTransactionTime().substring(0, 7))).get(1)+arr[k].getAmount();
				System.out.println(tempSpent);
				System.out.println(m1.get(arr[k].getTransactionTime().substring(0, 7)).get(1));
				(m1.get(arr[k].getTransactionTime().substring(0, 7))).remove(1);
				(m1.get(arr[k].getTransactionTime().substring(0, 7))).add(1, tempSpent);
				System.out.println(m1.get(arr[k].getTransactionTime().substring(0, 7)).get(1));
				//m1.put(arr[k].getTransactionTime().substring(0, 7), al);
			}
			
			else if(!m1.containsKey(arr[k].getTransactionTime().substring(0, 7)) && (arr[k].getAmount()>0)){
				//(m1.get(arr[k].getTransactionTime().substring(0, 7))).add(1, arr[k].getAmount());
				al.add(1, arr[k].getAmount());
				m1.put(arr[k].getTransactionTime().substring(0, 7),al);
			}

		}
		
		
		for (Map.Entry<String, ArrayList<Long>> entry : m1.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<Long> value = entry.getValue();
		    for(Long valLong : value){
		        System.out.println("key : " + key + " value : " + valLong);
		    }
		}
		
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
					for(int q=0;q<aObj.size()-1;q++){		
						if(aObj.get(q).getYyyyMm().matches(entry1.getKey())){
							aObj.get(q).setAmtEarned(entry1.getValue());
						}
					}
				}
				
		for(int p=0;p<aObj.size()-1;p++){
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

			//if((!t1.getMerchant().contains("DUNKIN #336784")) && (!t1.getMerchant().contains("Krispy Kreme Donuts")) )
				
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
					for(int q=0;q<aObj.size()-1;q++){		
						if(aObj.get(q).getYyyyMm().matches(entry1.getKey())){
							aObj.get(q).setAmtEarned(entry1.getValue());
						}
					}
				}
				
		for(int p=0;p<aObj.size()-1;p++){
			System.out.println(aObj.get(p).toString());
			}
			
		
		return aObj;
	}

	public static void loadAllData() throws IOException{

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://prod-api.level-labs.com/api/v2/core/get-all-transactions");

		post.addHeader("Accept", "application/json");
		post.addHeader("Content-type", "application/json");
		
		// if you need any parameters
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("uid", "1110590645"));
		urlParameters.add(new BasicNameValuePair("token", "9B006AA9FC0ACA764A2CDC6E29CF32DF"));
		urlParameters.add(new BasicNameValuePair("api-token", "AppTokenForInterview"));
		urlParameters.add(new BasicNameValuePair("json-strict-mode", "false"));
		urlParameters.add(new BasicNameValuePair("json-verbose-response", "false"));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		StringEntity input = new StringEntity("{\"args\": {\"uid\": 1110590645, \"token\": \"9B006AA9FC0ACA764A2CDC6E29CF32DF\", \"api-token\": \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}");
        input.setContentType("application/json");
        post.setEntity(input);
		
        for (NameValuePair h : urlParameters)
        {
            post.addHeader(h.getName(), h.getValue());
        }
        
		HttpResponse response = client.execute(post);

		response.toString();
		
		//System.out.println(response);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
		    result.append(line);
		//    System.out.println(line);
		}
		
		//System.out.println(result.toString());
		
		FileWriter fw = new FileWriter("/Users/Vidhi/Documents/workspace/carddetails/src/main/java/com/vidhi/cardtest/carddetails/Transactions.txt");
		fw.write(result.toString());
		fw.close();
		
    }
			
}