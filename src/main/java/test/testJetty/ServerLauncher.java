package test.testJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import fr.lordkadoc.launcher.ServerManager;

/**
 * Hello world!
 *
 */
public class ServerLauncher 
{
    public static void main( String[] args )
    {
        Server server = new Server(7777);
        JoinEndPoint.serverManager = new ServerManager();
        WebSocketHandler wsHandler = new WebSocketHandler()
	    {
	        @Override
	        public void configure(WebSocketServletFactory factory)
	        {
	            factory.register(JoinEndPoint.class);
	        }
	    };
	    server.setHandler(wsHandler);
	    try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
}
