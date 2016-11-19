package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import server.LuisResult.Entity;


public class Luis {

	String luisAddress = "https://api.projectoxford.ai/luis/v2.0/apps/f8f72586-5552-4a9b-83c1-765ceb1bbc3e?subscription-key="+HellO2.properties.getProperty("luisKey")+"&verbose=true";
	
	public Optional<LuisResult> query(String input){

		        URL luisURL;
				try {
					String[] words = input.split(" ");
					String inputQuery =  "&q=" + String.join("%20", words);
					luisURL = new URL(luisAddress + inputQuery);
			        URLConnection connection = luisURL.openConnection();

			        InputStreamReader in = new InputStreamReader(
                    		connection.getInputStream());
			       BufferedReader br= new BufferedReader(in); 
			       String inputLine;
			       String text = "";
			        while ((inputLine = br.readLine()) != null) {
			        	text += inputLine;
			        	System.out.println(inputLine);
			        }
			        in.close();
			        JSONObject jsonObj = new JSONObject(text);
			        JSONObject topIntentObj = jsonObj.getJSONObject("topScoringIntent");
			        String topIntent = topIntentObj.getString("intent");
			        JSONArray entities = jsonObj.getJSONArray("entities");
			        
			        ArrayList<Entity> entityObjs = new ArrayList<Entity>();
			        for(int i = 0; i < entities.length();i++) {
			        	JSONObject entity = entities.getJSONObject(i);
			        	String name = entity.getString("entity");
			        	String type = entity.getString("type");
			        	double score = 0;
			        	if(entity.has("score")){
			        		score = entity.getDouble("score");
			        	}
			        	String indices = String.valueOf(entity.getInt("startIndex")) + String.valueOf(entity.getInt("endIndex"));
			        	boolean gotit = false;
			        	for(Entity ent : entityObjs) {
			        		if(ent.indices.equals(indices)){
			        			gotit = true;
			        			break;
			        		}
			        	}
			        	if(!gotit){
			        		Entity ent = new Entity(name, type, score, indices);
			        		entityObjs.add(ent);
			        	}
			        }
			        
			        LuisResult result = new LuisResult(topIntent, entityObjs);
//			        System.out.println(topIntent);
			        return Optional.of(result);
			    

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return Optional.empty();
		    }

	
}
