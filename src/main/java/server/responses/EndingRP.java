package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class EndingRP extends BotResponse{

	String[] endings = {"Super, thank you!", "Great, thank you!","Sounds good, thank you"};
			

	public EndingRP(Context context, String forIntent) {
		super(context, forIntent, null );
	}

	@Override
	public String getInitResponse() {
		return getRandomAnswer(endings);
	}

	@Override
	public String getResponse(LuisResult lr) {
		return "";
	}

}
