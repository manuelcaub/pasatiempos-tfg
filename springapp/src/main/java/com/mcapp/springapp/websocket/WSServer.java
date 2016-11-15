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
	private static Hashtable<String, Session> sesiones=new Hashtable<>();
		
	@OnOpen
	public void open(Session session) {
		sesiones.put(session.getId(), session);
		JSONObject jso=new JSONObject();
		jso.put("type", "SESSION_ID");
		jso.put("sessionId", session.getId());
		send(session, jso);
		System.out.println("Ha llegado un pringao");
	}
	
	private void send(Session session, JSONObject jso) {
		try {
			session.getBasicRemote().sendText(jso.toString());
		} catch (IOException e) {
			sesiones.remove(session.getId());
		}
	}
	
	public static void send(String sessionId, JSONObject jso) {
		try {
			Session session=sesiones.get(sessionId);
			session.getBasicRemote().sendText(jso.toString());
		} catch (IOException e) {
			sesiones.remove(sessionId);
		}
	}

	@OnClose
	public void close(Session session) {
		sesiones.remove(session.getId());
		System.out.println("Se fue un pringao");
	}
}
