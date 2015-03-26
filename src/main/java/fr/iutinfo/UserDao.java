package fr.iutinfo;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface UserDao {
	@SqlUpdate("CREATE TABLE users (id integer primary key autoincrement, login text, mdp text, nom text, prenom text, age integer, formation text, FOREIGN KEY (formation) REFERENCES formations(libelle))")
	void createUser_Table();
	
	@SqlUpdate("CREATE TABLE amis (personne text, ami text, PRIMARY KEY (personne, ami), FOREIGN KEY (personne) REFERENCES utilisateurs(login), FOREIGN KEY (ami) REFERENCES utilisateurs(login));")
	void createAmis_Table();
	
	@SqlUpdate("CREATE TABLE matieres_GEII (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_GEII_Table();
	@SqlUpdate("CREATE TABLE matieres_GMP (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_GMP_Table();
	@SqlUpdate("CREATE TABLE matieres_INFORMATIQUE (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_INFORMATIQUE_Table();
	@SqlUpdate("CREATE TABLE matieres_MESURES_PHYSIQUES (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_MESURES_PHYSIQUES_Table();
	@SqlUpdate("CREATE TABLE matieres_GENIE_BIOLOGIQUE (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_GENIE_BIOLOGIQUE_Table();
	@SqlUpdate("CREATE TABLE matieres_CHIMIE (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_CHIMIE_Table();
	@SqlUpdate("CREATE TABLE matieres_GEA (mno integer, libelle text, PRIMARY KEY (mno));")
	void createMatieres_GEA_Table();
	
	@SqlUpdate("CREATE TABLE formations (libelle text, PRIMARY KEY (libelle));")
	void createFormations_Table();
	
	@SqlUpdate("INSERT into formations VALUES('GEII');")
	void insertFormations_GEII_Values();
	@SqlUpdate("INSERT into formations VALUES('GMP');")
	void insertFormations_GMP_Values();
	@SqlUpdate("INSERT into formations VALUES('INFORMATIQUE');")
	void insertFormations_INFORMATIQUE_Values();
	@SqlUpdate("INSERT into formations VALUES('MESURES PHYSIQUES');")
	void insertFormations_MESURES_PHYSIQUES_Values();
	@SqlUpdate("INSERT into formations VALUES('GENIE BIOLOGIQUE');")
	void insertFormations_GENIE_BIOLOGIQUE_Values();
	@SqlUpdate("INSERT into formations VALUES('CHIMIE');")
	void insertFormations_CHIMIE_Values();
	@SqlUpdate("INSERT into formations VALUES('GEA');")
	void insertFormations_GEA_Values();

	@SqlUpdate("CREATE TABLE topics_GEII (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEII(mno));")
	void createTopics_GEII_Table();
	@SqlUpdate("CREATE TABLE topics_GMP (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GMP(mno));")
	void createTopics_GMP_Table();
	@SqlUpdate("CREATE TABLE topics_INFORMATIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_INFORMATIQUE(mno));")
	void createTopics_INFORMATIQUE_Table();
	@SqlUpdate("CREATE TABLE topics_MESURES_PHYSIQUES (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_MESURES_PHYSIQUES(mno));")
	void createTopics_MESURES_PHYSIQUES_Table();
	@SqlUpdate("CREATE TABLE topics_GENIE_BIOLOGIQUE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GENIE_BIOLOGIQUE(mno));")
	void createTopics_GENIE_BIOLOGIQUE_Table();
	@SqlUpdate("CREATE TABLE topics_CHIMIE (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_CHIMIE(mno));")
	void createTopics_CHIMIE_Table();
	@SqlUpdate("CREATE TABLE topics_GEA (tno integer, formation text, matiere integer, PRIMARY KEY (tno), FOREIGN KEY (formation) REFERENCES formations(libelle), FOREIGN KEY (matiere) REFERENCES matieres_GEA(mno));")
	void createTopics_GEA_Table();
	
	@SqlUpdate("CREATE TABLE actualites (actuno integer, titre text, contenu text, date date, PRIMARY KEY (actuno));")
	void createActualites_Table();
	
	@SqlUpdate("CREATE TABLE messages (mno integer, message text, date date, titre text, PRIMARY KEY (mno));")
	void createMessages_Table();
	
	@SqlUpdate("CREATE TABLE annonces (ano integer, loginProprio text, message text, date date, titre text, PRIMARY KEY (ano));")
	void createAnnonces_Table();
	
	@SqlUpdate("INSERT into users  values (:id,:login,:mdp,:nom,:prenom,:type)")
	@GetGeneratedKeys
	int insertUser(@Bind("id") int id,@Bind("login") String login,@Bind("mdp") String mdp,@Bind("nom") String nom,@Bind("prenom") String prenom,@Bind("type") String type);
	
	@SqlUpdate("INSERT into annonces  values (:titre,:msg,:lieu)")
	@GetGeneratedKeys
	int insertAnnonces(@Bind("titre") String titre,@Bind("lmsg") String msg,@Bind("lieu") String lieu);
	
	@SqlQuery("SELECT * from users where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("nom") String nom);

	@SqlQuery("SELECT * from User where login = :login and mdp = :mdp")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User verifUser(@Bind("login") String pseudo, @Bind("mdp") String mdp);
	
	@SqlUpdate("DROP TABLE if exists users")
	void dropUserTable(); 
	
	void close();
}
