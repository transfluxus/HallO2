package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class GiveRP extends BotResponse{

	String[] inits = {"What exactly do you offer?"};

	
	public GiveRP(Context context, String forIntent, Input input) {
		super(context, forIntent, input);
	}
	
	@Override
	public String getInitResponse() {
		return getRandomAnswer(inits);
	}

	@Override
	public String getResponse(LuisResult lr) {
		fillEntities(lr);
		if(input.isComplete()){
			context.actual_response = this.next;
			return this.next.getInitResponse();
		} else {
			String missing = input.getMissing();
			
			if(missing.equals("object")){
				return "I am not sure if I understand you right. What items do you want to offer?";
			}
			if(missing.equals("amount")){
				System.out.println(input);
				return "Can you specify the amount?";
			} else {
				return "What and how much can you offer?";
			}
		}
	}

}
