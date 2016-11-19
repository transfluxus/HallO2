package server;

import http.ResponseBuilder;

import java.util.HashMap;
import java.util.Optional;

public class FacebookResponse extends ResponseBuilder {

	HashMap<String,BotResponse> responseBuilder= new HashMap<>();

	public FacebookResponse() {
		GreetingsRP greetings = new GreetingsRP("greetings");
		responseBuilder.put(greetings.forIntent,  greetings );
	}

	@Override
	public String getResponse(String requestBody) {
		return requestBody;
	}

}
