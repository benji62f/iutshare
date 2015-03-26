package fr.iutinfo;

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

	@Path("/annonce")
	@Produces(MediaType.APPLICATION_JSON)
	public class AnnoncesRessource {
		private static Map<Integer, Annonce> annonces = new HashMap<>();
		
		@POST
		public Annonce createAnnonces(Annonce annonce) {
			int id = annonces.size();
			annonce.setAno(id+1);
			annonces.put(annonce.getAno(), annonce);
			return annonce;
		}
		
		@DELETE
		@Path("{ano}")
		public Response deleteAnnonces(@PathParam("ano") Integer ano) {
			if (annonces.containsKey(ano)) {
				return Response.accepted().status(Status.ACCEPTED).build();
			}
		    return Response.accepted().status(Status.NOT_FOUND).build();
		}
		
		protected Annonce find(String titre) {
			Annonce out = null;
			for (Annonce annonce : annonces.values()) {
				if (annonce.getTitre().equals(titre)) {
					return annonce;
				}
			}
			return out;
		}
	
		protected Annonce find(int id) {
			return annonces.get(id);
		}
		
		@PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path("{ano}")
		public Response updateAnnonces(@PathParam("ano") int ano, 
				Annonce user) {
			Annonce oldAnnonces = find(ano);
			System.out.println("Should update user with id: "+ano
					+" ("+oldAnnonces+") to " +user);
			if (user == null) {
				throw new WebApplicationException(404);
			}
			oldAnnonces.setTitre(user.getTitre());
			return Response.status(200).entity(oldAnnonces).build();
		}
		
		@GET
		@Path("/{titre}")
		public Annonce getAnnonces(@PathParam("titre") String titre ) {
			Annonce out = find(titre);
			if (out == null) {
				throw new WebApplicationException(404);
			}
			return out;
		}
		
		
		@GET
		public List<Annonce> getAnnoncess(@DefaultValue("10") @QueryParam("limit") int limit) {
			return new ArrayList<Annonce>(annonces.values());
		}

	}


