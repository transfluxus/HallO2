package server.responses;

import server.BotResponse;
import server.BotResponse.Input;
import server.Context;
import server.LuisResult;

public class DeliverRP extends BotResponse{

	public DeliverRP(Context context, String forIntent, Input input) {
		super(context, forIntent, input);
	}

	@Override
	public String getInitResponse() {
		System.out.println(prev.getEntityValue("obj"));
		return "Perfect. In order to plan for your donation, we need to know the volume of your "+prev.getEntityValue("object") + ". How many moving boxes can you fill with your donation?";
	}
//
	@Override
	public String getResponse(LuisResult lr) {
		fillEntities(lr);
		if(input.isComplete()){
			context.actual_response = this.next;
			return this.next.getInitResponse();
		} else {
			String missing = input.getMissing();
			if(missing.equals("parcel")){
				return "I am not sure if I understand you right. How many boxes of stuff do you have?";
			}
			if(missing.equals("amount")){
				System.out.println(input);
				return "Can you specify the amount of moving boxes?";
			} else {
				return "I am not sure if I understand you right. Which NGO could you deliver it to?";
			}
		}
	}
	
}
