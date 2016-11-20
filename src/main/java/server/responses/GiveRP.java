package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class GiveRP extends BotResponse{

//	return "!?{\"intro\":\"Cool. There are several ways you can help. I will give you some examples. Which one do you like to choose?\",\"options\":[\"A Donate useful stuff\",\"B Donate money\",\"C Donate time\"]}";

	String[] inits = {"!?{\"intro\":\"In your neighbourhood, these are the things that are most needed by NGOs.\",\"options\":[\"A Writing materials like papers, pens and notepads\",\"B Toys like cars, dolls and stuffed animals\",\"C Winter shoes in all sizes\"]}"};

//	 writing
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
				return "I am not sure if I understand you right. Which of these items would you like to donate?";
			}
			if(missing.equals("amount")){
				System.out.println(input);
				return "Could you please specify the amount of items you want to donate?"; 
			} else {
				return "What and how much would you like to donate?";
			}
		}
	}

}
