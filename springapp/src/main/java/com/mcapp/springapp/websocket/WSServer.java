package com.mcapp.springapp.websocket;

import java.io.IOException;
import java.util.Hashtable;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

@ServerEndpoint("/servidorWS")
public class WSServer {
	private static Hashtable<String, Session> sesions=new Hashtable<>();
		
	@OnOpen
	public void open(Session session) {
		sesions.put(session.getId(), session);
		JSONObject jso=new JSONObject();
		jso.put("type", "SESSION_ID");
		jso.put("sessionId", session.getId());
		send(session, jso);
		System.out.println("Login " + session.getId());
	}
	
	private void send(Session session, JSONObject jso) {
		try {
			session.getBasicRemote().sendText(jso.toString());
		} catch (IOException e) {
			sesions.remove(session.getId());
		}
	}
	
	public static void send(String sessionId, JSONObject jso) {
		try {
			Session session=sesions.get(sessionId);
			session.getBasicRemote().sendText(jso.toString());
		} catch (IOException e) {
			sesions.remove(sessionId);
		}
	}

	@OnClose
	public void close(Session session) {
		sesions.remove(session.getId());
		System.out.println("Logout " + session.getId());
	}
}
