
public class Utilisateurs {
	private String nom;
	private String prenom;
	private String login;
	private int age;
	
	public Utilisateurs(String login,String prenom,String nom,int age){
		this.login=login;
		this.prenom=prenom;
		this.nom=nom;
		this.age=age;
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


	
	
	
}
