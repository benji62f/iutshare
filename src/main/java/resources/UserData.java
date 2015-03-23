package resources;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import prgm.User;

public interface UserData{
	
	@SqlUpdate("CREATE TABLE utilisateurs (login text, mdp text, nom text, prenom text, age integer, formation text, PRIMARY KEY (login), FOREIGN KEY (formation) REFERENCES formations(libelle))")
	void createUserTable();
	
	@SqlUpdate("CREATE TABLE amis (personne text, ami text, PRIMARY KEY (personne, ami), FOREIGN KEY (personne) REFERENCES utilisateurs(login), FOREIGN KEY (ami) REFERENCES utilisateurs(login))")
	void createAmisTable();
	
	@SqlUpdate("Insert into utilisateurs (name) values (:name)")
	@GetGeneratedKeys
	String insert(@Bind("name") String name);
	
	@SqlQuery("select * from utilisateurs where name = :name")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("name") String name);
	
	@SqlUpdate("drop Table if exists utilisateurs")
	void dropUserTable();
	
	void close();
}