import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yang.lolvideo.jetty.handler.LolHandler;


public class TestMain {
	private static final int PORT_ONE = 8087;
	private static final int PORT_TWO = 8088;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Spring config
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//ehcache config to get the object cache
		InputStream ehcacheConfig = TestMain.class.getResourceAsStream("ehcache.xml");
		CacheManager cacheManager = CacheManager.create(ehcacheConfig);
		Cache cache = cacheManager.getCache("sampleCache1");		
		
		//Jetty server to run the server 
		Server server =new Server();
		ServerConnector connector_one = new ServerConnector(server);
		connector_one.setPort(PORT_ONE);
		ServerConnector connector_two = new ServerConnector(server);
		connector_two.setPort(PORT_TWO);
		server.setConnectors(new Connector[]{connector_two});	
		Handler handler = new LolHandler(context,cache);
		server.setHandler(handler);
		//start server
		server.start();
		server.join();
	}
}
