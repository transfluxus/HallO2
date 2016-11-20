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
		return "!?{\"intro\":\"Cool. There are several ways you can help. I will give you some examples. Which one do you like to choose?\",\"options\":[\"A Donate useful stuff\",\"B Donate money\",\"C Donate time\"]}";
	}


	
}
