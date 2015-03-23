

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;



@ApplicationPath("/v1/")
public class App extends Application{
    @Override
    public Set<Class<?>> getClasses() {
    	
    	System.setProperty("jersey.config.server.tracing", "ALL");
    	
    	Set<Class<?>> s = new HashSet<Class<?>>();
    	/*on add les classes ressources */
    	 s.add(UserData.class);
    	 s.add(UserDataResource.class);
    	 s.add(UserResource.class);
    	
    	return s;
    }
    public static DBI dbi;
    static {
    SQLiteDataSource ds = new SQLiteDataSource();
    ds.setUrl("jdbc:sqlite:"/*+System "java.io.tmpdir")+System.("file.separator")+"*/+"database.db");
    dbi = new DBI(ds); 
    
    }
    }


