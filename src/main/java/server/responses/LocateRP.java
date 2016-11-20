package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class LocateRP extends BotResponse {

	String[] inits = {"What is your current location?"};
	
	public LocateRP(Context context, String forIntent, Input input) {
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
			context.setAspect("location",input.getEntityValue("location"));
			context.actual_response = this.next;
			return this.next.getInitResponse();
		} else {
			return "I am not sure if I understand you right. Where can you help?";
		}
	}

}
