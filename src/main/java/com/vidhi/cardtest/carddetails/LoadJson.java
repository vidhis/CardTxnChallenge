package com.vidhi.cardtest.carddetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class LoadJson {
	
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
