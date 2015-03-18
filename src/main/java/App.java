

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("/v1/")
public class App extends Application{
    @Override
    public Set<Class<?>> getClasses() {
    	
    	System.setProperty("jersey.config.server.tracing", "ALL");
    	
    	Set<Class<?>> s = new HashSet<Class<?>>();
    	/*on add les classes ressources 
    	 * s.add(.class)
    	 */
    	return s;
    }
}
