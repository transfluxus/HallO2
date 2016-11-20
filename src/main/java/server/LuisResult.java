package server;

import java.util.ArrayList;

public class LuisResult {

	String topIntent;
	public ArrayList<Entity> entities;

	
	public LuisResult(String topIntent, ArrayList<Entity> entities) {
		super();
		this.topIntent = topIntent;
		this.entities = entities;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Entity ent : entities){
			sb.append(ent.toString());
		}
		return "INTENT:"+topIntent+" -- "+sb.toString();
	}
	
	public static class Entity {
		
		String entity;
		String type;
		double score;
		String indices;
		
		public Entity(String entity, String type, double score,String indices) {
			super();
			this.entity = entity;
			this.type = type;
			this.score = score;
			this.indices = indices;
		}
		
		public String toString(){
			return " " + entity + "(" +type + ") ";
		}
		
	}

}
