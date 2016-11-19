package server;

import java.util.HashMap;
import java.util.Optional;

import http.ResponseBuilder;

public class SimpleResponse extends ResponseBuilder {

	HashMap<String,ResponseBuilder> responseBuilder= new HashMap<String,ResponseBuilder>();
	
	public SimpleResponse() {
		GreetingsRP greetings = new GreetingsRP("greetings");
		responseBuilder.put(greetings.forIntent,(ResponseBuilder) greetings );
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
				String response = Response
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
