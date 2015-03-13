package test.testJetty;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.*;

@WebSocket
	public class testEndPoint {
		
		@OnWebSocketConnect
		public void handleConnect(Session session){
			System.out.println("Client connecté : " + session);
		}
		
		@OnWebSocketClose
		public void handleClose(Session session, int code, String reason){
			System.out.println(code + " Client déconnecté : " + reason);
		}
		
		@OnWebSocketError
		public void handleError(Session session, Throwable t){
			System.out.println("Erreur client : " + session);
			t.printStackTrace();
		}
		
		@OnWebSocketMessage
		public void handleMessage(Session session, String message){
			System.out.println("Message du client : " + session);
			System.out.println(message);
		}
		
	}