import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)

public class UserDataResource {
	private static UserData data = App.dbi.open(UserData.class);
	public UserDataResource() {
		
		try {
			data.createUserTable();
		}catch (Exception e) {
		
		System.out.println("Table déjà là !");
		}
		
		//data.insert("Ayouub");
	}
	
	@POST
	public User createUser(User user) {
		data.insert(user.getLogin(),user.getMdp(),user.getNom(),user.getPrenom(),user.getType());
		
		
		return user;
	}
	
	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {
		User out = data.findByName(name);
		if (out == null) {
			throw new WebApplicationException(404);
		}
		
		return out;
}
}