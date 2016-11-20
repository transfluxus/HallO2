package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import http.ResponseBuilder;

public class SimpleResponse extends ResponseBuilder {

	String lastRequestBody = "";

	@Override
	public String getResponse(String requestBody) {
		System.out.println("");
//		System.out.println(this);
		System.out.println("#"+requestBody);
		try {
			if(lastRequestBody.equals(requestBody)){
				System.out.println("twice");
				return "NO";
			}
//			System.out.println(requestBody);

			Map<String, String> paramsRes = new HashMap<String, String>();
			String[] params = requestBody.split("&");
			for (String param : params) {
				String pair[] = param.split("=");
				if (pair.length > 1) {
					paramsRes.put(pair[0], pair[1]);
				} else {
					paramsRes.put(pair[0], "");
				}
			}
			lastRequestBody = new String(requestBody);
			
//			System.out.println(paramsRes.get("input"));
//			System.out.println(paramsRes.get("id"));
			Optional<LuisResult> result = HellO2.luis.query(paramsRes.get("input"));
					
//			System.out.println(BotConnect.query(input));
			if (result.isPresent()) {
				System.out.println(result.toString());
				Context context = HellO2.sessions.get(Long.decode(paramsRes.get("id")));
				if(result.isPresent()){
//					System.out.println(context);
					return context.getResponse(result.get());
				} else {
					return "No LUIS No Chat!";
				}
//				String intent = result.get().topIntent;
//				if(responseBuilder.containsKey(intent)){
//					Session session = HellO2.sessions.get(Long.decode(paramsRes.get("id")));
//					return responseBuilder.get(intent).getResponse(session,result.get());
//				}
				
//				String response = Response
				// INSERT AI HERE!
			} else {
				return "I am a bot";
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "Hi, Nice to meet you";
	}

}
