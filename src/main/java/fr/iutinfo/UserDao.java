package fr.iutinfo;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, login text, mdp text, nom text, prenom text, age integer, formation text, FOREIGN KEY (formation) REFERENCES formations(libelle))")
	void createUserTable();

	@SqlUpdate("insert into users  values (:id,:login,:mdp,:nom,:prenom,:age,:formation)")
	@GetGeneratedKeys
	int insert(@Bind("id") int id,@Bind("login") String login,@Bind("mdp") String mdp,@Bind("nom") String nom,@Bind("prenom") String prenom,@Bind("age") int age,@Bind("formation") String formation);

	@SqlQuery("select * from users where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("nom") String nom);

	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 
	
	void close();
}
