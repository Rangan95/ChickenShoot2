package test.testJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server(7777);
        WebSocketHandler wsHandler = new WebSocketHandler()
	    {
	        @Override
	        public void configure(WebSocketServletFactory factory)
	        {
	            factory.register(testEndPoint.class);
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
