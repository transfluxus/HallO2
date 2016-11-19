package server;

public class GreetingsRP extends BotResponse {

	public GreetingsRP(String forIntent) {
		super(forIntent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getResponse(LuisResult lr) {
		return "Hello there";
	}

	
}
