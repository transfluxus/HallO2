package server;

import java.util.Random;

public abstract class BotResponse {

	String forIntent;

	public BotResponse(String forIntent) {
		this.forIntent = forIntent;
	}
	
	public abstract String getResponse(LuisResult lr);
	
	public String getRandomAnswer(String[] poss){
		Random rnd = new Random();
		return poss[rnd.nextInt(poss.length)];
	}
}
