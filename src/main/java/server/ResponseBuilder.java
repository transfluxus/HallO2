package server;

public abstract class ResponseBuilder {

	String forIntent;

	public ResponseBuilder(String forIntent) {
		this.forIntent = forIntent;
	}
	
	public String getResponse(LuisResult lr){
		return "Answer";
	}
}
