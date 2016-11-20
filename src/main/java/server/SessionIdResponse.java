package server;

import http.ResponseBuilder;

public class SessionIdResponse extends ResponseBuilder {

	@Override
	public String getResponse(String arg0) {
		Context session = new Context();
		HellO2.sessions.put(session.sessionId, session);
//		System.out.println(session.sessionId);
		return String.valueOf(session.sessionId);
	}

}
