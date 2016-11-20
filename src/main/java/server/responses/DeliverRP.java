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
		return "What do you need in order to deliver the "+prev.getEntityValue("object")+"?";
	}

	@Override
	public String getResponse(LuisResult lr) {
		fillEntities(lr);
		if(input.isComplete()){
			context.actual_response = this.next;
			return this.next.getInitResponse();
		} else {
			String missing = input.getMissing();
			if(missing.equals("parcel")){
				return "I am not sure if I understand you right. How should it be transported?";
			}
			if(missing.equals("amount")){
				System.out.println(input);
				return "Can you specify the amount of containers?";
			} else {
				return "I am not sure if I understand you right. Who should it be transported?";
			}
		}
	}
	
}
