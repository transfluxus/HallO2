package server;

public class GreetingsRP extends BotResponse {

	String[] answers = {"Hello there", "Hi, how can I help you?", "Hi!", "Hello Hello"};
	
	public GreetingsRP(String forIntent) {
		super(forIntent);
	}

	@Override
	public String getResponse(LuisResult lr) {
		return getRandomAnswer(answers);
	}

}
