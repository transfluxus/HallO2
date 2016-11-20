package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class EndingRP extends BotResponse{

	String[] endings = {"Awesome! Thanks so much for your contribution. Show that you are a part of “Germany can do” by spreading the message"};
			

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
