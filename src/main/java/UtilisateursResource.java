

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
public class UtilisateursResource {
	private static Map<Integer,UtilisateursData > users = new HashMap<>();
	
	@POST
	public UtilisateursData create(UtilisateursData user) {
		int id = users.size();
		users.put(id+1, user);
		return user;
	}
	
	@DELETE
	@Path("{id}")
	public Response Delete(@PathParam("id") Integer id) {
		if (users.containsKey(id)) {
			return Response.accepted().status(Status.ACCEPTED).build();
		}
	    return Response.accepted().status(Status.NOT_FOUND).build();
	}
	
	protected UtilisateursData Recherche_user(String name) {
		UtilisateursData us = null;
		for (UtilisateursData user : users.values()) {
			if (user.getNom().equals(name)) {
				return user;
			}
		}
		return us;
	}
	protected UtilisateursData Recherche_user(int id) {
		return users.get(id);
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
	public Response Update(@PathParam("id") int id, 
			UtilisateursData user) {
		UtilisateursData ancien = Recherche_user(id);
		System.out.println("Update user: "+id
				+" ("+ancien+"). new user " +user);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		ancien.setNom(user.getNom());
		return Response.status(200).entity(ancien).build();
	}
	
	@GET
	@Path("/{name}")
	public UtilisateursData getUser(@PathParam("name") String name ) {
		UtilisateursData us = Recherche_user(name);
		if (us == null) {
			throw new WebApplicationException(404);
		} 
		return us;
	}
	
	@GET
	public List<UtilisateursData> getUsers(@DefaultValue("1") @QueryParam("limit") int limit) {
		return new ArrayList<UtilisateursData>(users.values());
	}

}