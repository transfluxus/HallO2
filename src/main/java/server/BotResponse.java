package server;

public abstract class BotResponse {

	String forIntent;

	public BotResponse(String forIntent) {
		this.forIntent = forIntent;
	}
	
	public String getResponse(LuisResult lr){
		return "Answer";
	}
}
