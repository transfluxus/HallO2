package server;

import java.util.HashMap;
import java.util.Optional;

import http.ResponseBuilder;

public class SimpleResponse extends ResponseBuilder {

	HashMap<String,BotResponse> responseBuilder= new HashMap<>();
	
	public SimpleResponse() {
		GreetingsRP greetings = new GreetingsRP("greetings");
		responseBuilder.put(greetings.forIntent,  greetings );
	}

	@Override
	public String getResponse(String requestBody) {
		try {
			String input = requestBody.split("=")[1];
			input = String.join(" ", input.split("\\+"));
			System.out.println(input);
			Optional<LuisResult> result = HellO2.luis.query(input);
					
//			System.out.println(BotConnect.query(input));
			if (result.isPresent()) {
				System.out.println(result.toString());
				String intent = result.get().topIntent;
				if(responseBuilder.containsKey(intent)){
					return responseBuilder.get(intent).getResponse(result.get());
				}
//				String response = Response
				// INSERT AI HERE!
			} else {
				return "I am a bot";
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "Hi, Nice to meet you";
	}

}
