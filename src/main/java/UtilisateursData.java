public class UtilisateursData {
	private String nom;
	private String prenom;
	private String pseudo;
	private String mdp;
	private String type; /* admin ou utilisateur */
	

	public UtilisateursData(String nom,String prenom, String pseudo, String mdp,String type) {
		
		this.nom = nom;
		this.prenom=prenom;
		this.pseudo=pseudo;
		this.mdp=mdp;
		this.type=type;
				
	}

	public UtilisateursData() {
		
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String getType() {
		return type;
	}

	public void setTypeUser(String typeUser) {
		this.type = typeUser;
	}

	public String toString() {
		return nom+ " " +prenom+ " pseudo: "+pseudo+" mot de passe "+mdp+" . Bienvenue  "+type;
	}
}