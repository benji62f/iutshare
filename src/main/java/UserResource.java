

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private static Map<String,User > users = new HashMap<>();
	
	@POST
	public User create(User user) {
		
		users.put(user.getLogin(), user);
		return user;
	}
	
	@DELETE
	@Path("{login}")
	public Response deleteUser(@PathParam("login") String login) {
		if (users.containsKey(login)) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
	
		return Response.accepted().status(Status.NOT_FOUND).build();
	}
	
	protected User find(String name) {
		User usr = null;
		for (User user : users.values()) {
				if (user.getNom().equals(name)) {
				return user;
				}
	}
	return usr;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{login}")
	public Response updateUser(@PathParam("login") String login,User user) {
		User oldUser = find(login);
		System.out.println("Should update user with id: "+login+" ("+oldUser+") to " +user);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		oldUser.setNom(user.getNom());
		return Response.status(200).entity(oldUser).build();
	}
	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name ) {
		User usr = find(name);
		if (usr == null) {
			throw new WebApplicationException(404);
		}
		
		return usr;
	}
	
	@GET
	public List<User> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
		return new ArrayList<User>(users.values());
	}
}