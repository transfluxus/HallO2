package server;

import java.util.HashMap;

import server.BotResponse.Input;
import server.responses.DeliverRP;
import server.responses.EndingRP;
import server.responses.GiveRP;
import server.responses.GreetingsRP;
import server.responses.HelpRP;
import server.responses.LocateRP;
import server.responses.OrganizationRP;

public class Context {

	public final long sessionId;
	
	GreetingsRP greetings;
	HelpRP help;
	LocateRP locate;
	GiveRP give;
	DeliverRP deliver;
	OrganizationRP organisation;
	EndingRP ending;
	
	public BotResponse actual_response;
	
	HashMap<String, String> aspects = new HashMap<>();
	
	static long nextSessionId = 0;
	
	public Context() {
		sessionId = nextSessionId++;
		
		greetings = new GreetingsRP(this, "greetings");
		
		help = new HelpRP(this, "help", new Input("donate"));
		greetings.setNext(help);
		
		locate = new LocateRP(this,"locate", new Input("location"));
		help.next = locate;
		
		give = new GiveRP(this,"give",new Input(new String[]{"object","amount"}));
		locate.next = give;
		
		deliver = new DeliverRP(this,"deliver",new Input(new String[]{"parcel","builtin.number"}));
		give.setNext(deliver);	
		
		organisation = new OrganizationRP(this, "organisation");
		deliver.setNext(organisation);
			
		ending = new EndingRP(this, "ending");
		organisation.setNext(ending);
		
		actual_response = help;
	}

	public String getResponse(LuisResult result) {
		System.out.println(">> "+actual_response.forIntent);
		return actual_response.getResponse(result);
	}

	public String getAspect(String name) {		
		return aspects.get(name);
	}

	public void setAspect(String name, String value) {
		aspects.put(name, value);
	}

}
