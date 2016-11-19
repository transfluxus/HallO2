package server;

import java.util.Arrays;

import http.ResponseBuilder;
import processing.core.PApplet;

public class SimpleResponse extends ResponseBuilder {

	
	public SimpleResponse(){
	}
	
	@Override
	public String getResponse(String requestBody) {
		try {
			String input = requestBody.split("=")[1];
			input = String.join(" ",input.split("\\+"));
			System.out.println(input);
//		     INSERT AI HERE!
			return "I am a bot";
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return "Hi, Nice to meet you";
	}

}
