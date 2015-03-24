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
	
    public static Connection connection;
    private static String requete;
     
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
     * @throws ClassNotFoundException 
     */
    public static Connection connect() throws ClassNotFoundException{
        try {
            // Etabli la connection
        	Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            // D�clare l'objet qui permet de faire les requ�tes
           
             
            // Le PRAGMA synchronous de SQLite permet de v�rifier chaque �criture
            // avant d'en faire une nouvelle.
            // Le PRAGMA count_changes de SQLite permet de compter le nombre de
            // changements fait sur la base
            // R�sultats de mes tests :
            // synchronous OFF, une insertion est 20 fois plus rapide.
            // La diff�rences avec le count_changes est de l'ordre de la �s.
            // Les autres PRAGMA : http://www.sqlite.org/pragma.html
             
         //   requete.executeUpdate("PRAGMA synchronous = OFF;");
        //    requete.setQueryTimeout(30);
            
        } catch(SQLException e){
            // message = "out of memory" souvent le resultat de la BDD pas trouv�e
            e.printStackTrace();
            
        }
		return connection;
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
   /* public ResultSet getResultOf(String requete) {
        try {
            return this.requete.executeQuery(requete);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }*/
 
    /**
     * Permet de modifier une entr�e de la base de donn�es.
     * @param requete La requete SQL de modification
     * @throws ClassNotFoundException 
     */
   /* public void updateValue(String requete) {
        try {
            this.requete.executeUpdate(requete);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }*/
    
    public static void initBDD() throws ClassNotFoundException {
		Connection c=connect();
		Statement stmt=null;
		try{
			stmt=connection.createStatement();
			requete ="CREATE TABLE amis (personne text, ami text, PRIMARY KEY (personne, ami), FOREIGN KEY (personne) REFERENCES utilisateurs(login), FOREIGN KEY (ami) REFERENCES utilisateurs(login));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_GEII (mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_GMP (mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_INFORMATIQUE (mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_MP (mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_GENIE_BIOLOGIQUE(mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_CHIMIE(mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE matieres_GEA(mno integer, libelle text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE formations (libelle text, PRIMARY KEY (libelle));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE formations (libelle text, PRIMARY KEY (libelle));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('GEII');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('GMP');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('INFORMATIQUE');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('MP');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('GENIE_BIOLOGIQUE');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('CHIMIE');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="INSERT into formations VALUES('GEA');";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE utilisateurs (login text, mdp text, nom text, prenom text, age integer, formation text, PRIMARY KEY (login), FOREIGN KEY (formation) REFERENCES formations(libelle));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_GEII (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_GMP (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_INFORMATIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_MP (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_GENIE_BIOLOGIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_CHIMIE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE topics_GEA (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));";
			stmt.executeUpdate(requete);
			
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE actualites (actuno integer, titre text, contenu text, date date, PRIMARY KEY (actuno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE messages (mno integer, message text, date date, titre text, PRIMARY KEY (mno));";
			stmt.executeUpdate(requete);
			
			stmt=connection.createStatement();
			requete ="CREATE TABLE annonces (ano integer, loginProprio text, message text, date date, titre text, PRIMARY KEY (ano));";
			stmt.executeUpdate(requete);	
			
			stmt.close();
			connection.close();
			
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}