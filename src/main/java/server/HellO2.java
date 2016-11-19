package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import http.DynamicResponseHandler;
import http.SimpleHTTPServer;
//import processing.core.PApplet;

public class HellO2{
	
	SimpleHTTPServer server;
//	Luis luis = new 
	
	public HellO2() {
		server = new SimpleHTTPServer();
//		server.setLoggerLevel(Level.ALL);
		server.serve("style.css");
		DynamicResponseHandler responder = new DynamicResponseHandler(new SimpleResponse(), "application/json");
		server.createContext("submit", responder);
		executeCommand("python test.py");
	}

	public static void main(String[] args) {
		new HellO2();
	}
	
	
	private static String executeCommand(String command) {
		System.out.println(command);
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			output.append(getStreamAsText(p.getInputStream()));
			output.append(getStreamAsText(p.getErrorStream()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return output.toString();	
	}
	
	public static String getStreamAsText(InputStream stream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuffer output = new StringBuffer();
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
