package test.testJetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import fr.lordkadoc.launcher.ServerInstance;
import fr.lordkadoc.launcher.ServerManager;

@WebSocket
	public class JoinEndPoint {
	
		public static ServerManager serverManager;
		public static List<Session> users = new ArrayList<Session>();
		
		@OnWebSocketConnect
		public void handleConnect(Session session){
			users.add(session);
			System.out.println("Connexion d'un utilisateur : " + session.getLocalAddress());
		}
		
		@OnWebSocketClose
		public void handleClose(Session session, int code, String reason){
			users.remove(session);
			System.out.println("Déconnexion d'un utilisateur");
		}
		
		@OnWebSocketError
		public void handleError(Session session, Throwable t){
			System.out.println("Erreur client : " + session);
			t.printStackTrace();
		}
		
		@OnWebSocketMessage
		public void handleMessage(Session session, String message){
			if(users.contains(session)){
				if(message.equals("join")){
					ServerInstance instance = serverManager.getFreeInstance();
					if(instance !=null){		
						instance.ajouterJoueur(session);
					}else{
						try {
							String s = "Les parties sont toutes complètes. Essayez plus tard ou créez en une.";
							session.getRemote().sendString((Json.createBuilderFactory(null).createObjectBuilder().add("type", "erreur").add("data", s).build()).toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else if(message.equals("create")){
					serverManager.ajouterInstance(session);
				}else{
					serverManager.getPlayerInstance(session).recevoirMessage(session, message);
				}
			}
		}
		
	}