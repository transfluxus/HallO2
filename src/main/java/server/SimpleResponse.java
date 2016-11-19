package server;

import java.util.Optional;

import http.ResponseBuilder;

public class SimpleResponse extends ResponseBuilder {

	public SimpleResponse() {
	}

	@Override
	public String getResponse(String requestBody) {
		try {
			String input = requestBody.split("=")[1];
			input = String.join(" ", input.split("\\+"));
			System.out.println(input);
			Optional<LuisResult> result = HellO2.luis.query(input);
			if (result.isPresent()) {
				System.out.println(result.toString());
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
