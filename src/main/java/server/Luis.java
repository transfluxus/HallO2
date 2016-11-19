package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Luis {

	String luisAddress = "https://api.projectoxford.ai/luis/v2.0/apps/f8f72586-5552-4a9b-83c1-765ceb1bbc3e?subscription-key="+HellO2.properties.getProperty("luisKey")+"&verbose=true";
	
	public void query(String input){

		        URL luisURL;
				try {
					String[] words = input.split(" ");
					String inputQuery =  "&q=" + String.join("%20", words);
					luisURL = new URL(luisAddress + inputQuery);
			        URLConnection connection = luisURL.openConnection();
			        BufferedReader in = new BufferedReader(
			                                new InputStreamReader(
			                                		connection.getInputStream()));
			        String inputLine;

			        while ((inputLine = in.readLine()) != null) 
			            System.out.println(inputLine);
			        in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }

	
}
