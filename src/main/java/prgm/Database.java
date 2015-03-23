package prgm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * Cette classe fait l'interface avec la base de donn�es.
 *
 */
public class Database {
	
    public Connection connection;
    private Statement requete;
     
    /**
     * Constructeur de la classe Database
     * @param dbName Le nom de la base de donn�es
     */
    public Database(){
        // Charge le driver sqlite JDBC en utilisant le class loader actuel
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e1){
            System.err.println(e1.getMessage());
        }
 
        this.connection = null;
    }
     
    /**
     * Ouvre la base de donn�es sp�cifi�e
     * @return True si la connection a �t� r�ussie. False sinon.
     */
    public boolean connect(){
        try {
            // Etabli la connection
			connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            // D�clare l'objet qui permet de faire les requ�tes
            requete = connection.createStatement();
             
            // Le PRAGMA synchronous de SQLite permet de v�rifier chaque �criture
            // avant d'en faire une nouvelle.
            // Le PRAGMA count_changes de SQLite permet de compter le nombre de
            // changements fait sur la base
            // R�sultats de mes tests :
            // synchronous OFF, une insertion est 20 fois plus rapide.
            // La diff�rences avec le count_changes est de l'ordre de la �s.
            // Les autres PRAGMA : http://www.sqlite.org/pragma.html
             
            requete.executeUpdate("PRAGMA synchronous = OFF;");
            requete.setQueryTimeout(30);
             
            return true;
        } catch(SQLException e){
            // message = "out of memory" souvent le resultat de la BDD pas trouv�e
            e.printStackTrace();
            return false;
        }
    }
     
    /**
     * Ferme la connexion � la base de donn�es
     * @return True si la connexion a bien �t� ferm�e. False sinon.
     */
    public boolean disconnect() {
        try {
            if(connection != null)
                connection.close();
             
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
     
    /**
     * Permet de faire une requ�te SQL
     * @param requete La requ�te SQL (avec un ";" � la fin)
     * @return Un ResultSet contenant le r�sultat de la requ�te
     */
    public ResultSet getResultOf(String requete) {
        try {
            return this.requete.executeQuery(requete);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * Permet de modifier une entr�e de la base de donn�es.
     * @param requete La requete SQL de modification
     */
    public void updateValue(String requete) {
        try {
            this.requete.executeUpdate(requete);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		Database db = new Database();
		db.connect();

		db.updateValue("CREATE TABLE amis (personne text, ami text, PRIMARY KEY (personne, ami), FOREIGN KEY (personne) REFERENCES utilisateurs(login), FOREIGN KEY (ami) REFERENCES utilisateurs(login));");
		
		db.updateValue("CREATE TABLE matieres_GEII (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_GMP (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_INFORMATIQUE (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_MESURES_PHYSIQUES (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_GENIE_BIOLOGIQUE (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_CHIMIE (mno integer, libelle text, PRIMARY KEY (mno));");
		db.updateValue("CREATE TABLE matieres_GEA (mno integer, libelle text, PRIMARY KEY (mno));");
		
		db.updateValue("CREATE TABLE formations (libelle text, PRIMARY KEY (libelle));");
		db.updateValue("INSERT into formations VALUES('GEII');");
		db.updateValue("INSERT into formations VALUES('GMP');");
		db.updateValue("INSERT into formations VALUES('INFORMATIQUE');");
		db.updateValue("INSERT into formations VALUES('MESURES PHYSIQUES');");
		db.updateValue("INSERT into formations VALUES('GENIE BIOLOGIQUE');");
		db.updateValue("INSERT into formations VALUES('CHIMIE');");
		db.updateValue("INSERT into formations VALUES('GEA');");
		
		db.updateValue("CREATE TABLE utilisateurs (login text, mdp text, nom text, prenom text, age integer, formation text, PRIMARY KEY (login), FOREIGN KEY (formation) REFERENCES formations(libelle));");
		
		db.updateValue("CREATE TABLE topics_GEII (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));");
		db.updateValue("CREATE TABLE topics_GMP (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GMP(mno));");
		db.updateValue("CREATE TABLE topics_INFORMATIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_INFORMATIQUE(mno));");
		db.updateValue("CREATE TABLE topics_MESURES_PHYSIQUES (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_MESURES_PHYSIQUES(mno));");
		db.updateValue("CREATE TABLE topics_GENIE_BIOLOGIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GENIE_BIOLOGIQUE(mno));");
		db.updateValue("CREATE TABLE topics_CHIMIE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_CHIMIE(mno));");
		db.updateValue("CREATE TABLE topics_GEA (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEA(mno));");

		
		db.updateValue("CREATE TABLE actualites (actuno integer, titre text, contenu text, date date, PRIMARY KEY (actuno));");
		
		db.updateValue("CREATE TABLE messages (mno integer, message text, date date, titre text, PRIMARY KEY (mno));");
		
		db.updateValue("CREATE TABLE annonces (ano integer, loginProprio text, message text, date date, titre text, PRIMARY KEY (ano));");
		
		db.disconnect();
	}
}