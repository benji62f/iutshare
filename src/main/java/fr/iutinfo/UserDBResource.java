package fr.iutinfo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.tbe.json.ObjetBoolean;
import com.tbe.json.ObjetjSON;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)
public class UserDBResource {
	private static UserDao data = App.dbi.open(UserDao.class);

	public UserDBResource() {
		try {
			data.createUserTable();
		} catch (Exception e) {
			System.out.println("Table déjà là !");
		}
		//data.insert(1,"foo","aaa","coucou","coucou",15,"coucou");
	}
	
	@POST
	public User createUser(User user) {
		int id = data.insert(user.getId(),user.getLogin(),user.getMdp(),user.getNom(),user.getPrenom(),user.getType());
		user.setId(id);
		return user;
	}

	@GET
	@Path("/getUserName/{name}")
	public User getUser(@PathParam("name") String name) {
		User out = data.findByName(name);
		if (out == null) {
			throw new WebApplicationException(404);
		}
		return out;
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/connexion")
	public ObjetBoolean getUser(ObjetjSON oj) {
		System.out.println("Getting user : " + oj.getLogin() + " " + oj.getPassword());
		return new ObjetBoolean(data.verifUser(oj.getLogin(), oj.getPassword()));
	}

}
