package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class HelpRP extends BotResponse{

	String definite_extension = "How do you want to help?";
		
	public HelpRP(Context context, String forIntent, Input input) {
		super(context, forIntent, input);
	}

	@Override
	public String getResponse(LuisResult lr) {
		fillEntities(lr);
		System.out.println("complete: " + input.isComplete());
		if(input.isComplete()){
			context.actual_response = this.next;
			return this.next.getInitResponse();
		} else {
			return "I am not sure if I understand you right. What can you offer?";
		}
	}

	@Override
	public String getInitResponse() {
		return "How do you want to help?";
	}


	
}
