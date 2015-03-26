package fr.iutinfo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("/annoncesdb")
@Produces(MediaType.APPLICATION_JSON)
public class AnnoncesDBResource {
	private static UserDao data = App.dbi.open(UserDao.class);

	public AnnoncesDBResource() {
		try {
			data.createAnnonces_Table();
		} catch (Exception e) {
			System.out.println("Table déjà là !");
		}
		
	}
	/*
	@POST
	public Annonce createAnnonces(Annonce annonce) {
		int id = data.insertAnnonces(annonce.getTitre(), annonce.getMsg(), annonce.getLieu());
		annonce.setAno(id);
		return annonce;
	}
*/
	
}
