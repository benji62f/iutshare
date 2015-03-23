package resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import prgm.App;
import prgm.User;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)
public class UserDBResource {
	private static UserData data=App.dbi.open(UserData.class);
	
	public UserDBResource(){
		try{
			data.createUserTable();
			
		}catch(Exception e){
			System.out.println(" Table existe déja ");
		}
		
		data.insert("Ayoub");
	}
	
	@POST 
	public User createUser(User user){
		String login=data.insert(user.getNom());
		user.setLogin(login);
		return user;
	}
	
	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name){
		User us=data.findByName(name);
		if(us==null){
			throw new WebApplicationException(404);
		}
		return us;
	}
}
