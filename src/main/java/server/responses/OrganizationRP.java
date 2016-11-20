package server.responses;

import server.BotResponse;
import server.Context;
import server.LuisResult;

public class OrganizationRP extends BotResponse {

	public OrganizationRP(Context context, String forIntent, Input input) {
		super(context, forIntent, input);
	}

	public OrganizationRP(Context context, String forIntent) {
		super(context, forIntent, null);
	}

	@Override
	public String getInitResponse() {
		return "There is an organisation in " + context.getAspect("location") + ".\nCenter for Refugees NGO\nAddress: Jingoostraße 16.\nOpen every day from 8Am - 6PM.";
	}

	@Override
	public String getResponse(LuisResult lr) {
		return next.getInitResponse();
	}

}
