package fr.iutinfo;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String login;
	private int age;
	private String type;
	private String mdp;
	private String formation;

	public User(int id,String login,String prenom,String nom,int age,String type,String mdp,String formation){
		this.id=id;
		this.login=login;
		this.prenom=prenom;
		this.nom=nom;
		this.age=age;
		this.type=type;
		this.mdp=mdp;
		this.formation=formation;
	}

	public User() {
		
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

}
