package server;

import java.util.Random;

import server.LuisResult.Entity;

public abstract class BotResponse {

	public Context context;
	public String forIntent;
	
	protected final Input input;
	
	public BotResponse next;
	public BotResponse prev;

	public BotResponse(Context context, String forIntent,Input input) {
		this.forIntent = forIntent;
		this.input = input;
		this.context = context;
	}
	
	public abstract String getInitResponse();
	
	public abstract String getResponse(LuisResult lr);
	
	public void fillEntities(LuisResult lr){
		for(Entity entity : lr.entities){
			if(input.requiresEntity(entity)){
				input.set(entity);
				System.out.println("setting: "+entity);
			}
		}
		System.out.println(input);
	}
	
	public String getRandomAnswer(String[] poss){
		Random rnd = new Random();
		return poss[rnd.nextInt(poss.length)];
	}
	
	public boolean requiresEntity(Entity entity){
		return input.requiresEntity(entity);
	}
	
	public String getEntityValue(String entityName){
		return input.getEntityValue(entityName);
	}
	
	public static class Input {
		
		final String[] entityNames;
		String[] values;
		boolean received = false;
		
		
		public Input(String entityName) {
			this(new String[] {entityName});
		}	
		
		public String getEntityValue(String entityName) {
			int i = 0;
			for(String ents : entityNames){
				if(ents.equals(entityName)){
					return values[i];
				}
				i++;
			} 
				return "";
		}

		public Input(String [] entityNames) {
			super();
			this.entityNames = entityNames;
			this.values = new String[this.entityNames.length];
		}	
		
		public boolean requiresEntity(Entity entity){
			for(String ents : entityNames){
				if(ents.equals(entity.type))
					return true;
			}
			return false;
		}

		public void set(Entity entity) {
			int i = 0;
			for(String ents : entityNames){
				if(ents.equals(entity.type)){
					values[i] = entity.entity;
				}
				i++;
			}
		}
		
		public boolean isComplete(){
			for(int i=0; i < values.length; i++){
				if(values[i] == null){
					return false;
				}
			}
			return true;
		}
		
		public String getMissing(){
			for(int i=0; i < values.length; i++){
				if(values[i] == null){
					return entityNames[i];
				}
			}
			return "";
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < values.length; i++) {
				sb.append(entityNames[i] + ":"+values[i] + " ");
			}
			return sb.toString();
		}	
	}
	
	public void setNext(BotResponse next) {
		this.next = next;
		next.prev = this;
	}
	
}
