package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class GreetingsRP extends BotResponse {

//	String[] answers = {"Hello there.", "Hi, how can I help you?", "Hi!", "Hello Hello."};
	String definite_extension = "How do you want to help?";

	
	public GreetingsRP(Context context, String forIntent) {
		super(context, forIntent, null);
	}

	
	@Override
	public String getResponse(LuisResult lr) {
		context.actual_response = this.next;
		return this.next.getInitResponse();
	}

	@Override
	public String getInitResponse() {
		return "hi";
	}

}
