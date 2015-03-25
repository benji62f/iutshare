package fr.iutinfo;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import org.skife.jdbi.v2.unstable.BindIn;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, login text, mdp text, nom text, prenom text, age integer, formation text, FOREIGN KEY (formation) REFERENCES formations(libelle))")
	void createUserTable();

	@SqlUpdate("insert into users  values (:id,:login,:mdp,:nom,:prenom,:type)")
	@GetGeneratedKeys
	int insert(@Bind("id") int id,@Bind("login") String login,@Bind("mdp") String mdp,@Bind("nom") String nom,@Bind("prenom") String prenom,@Bind("type") String type);

	@SqlQuery("select * from users where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("nom") String nom);

	@SqlQuery("select * from User where login = :login and mdp = :mdp")
	@RegisterMapperFactory(BeanMapperFactory.class)
	boolean verifUser(@Bind("login") String pseudo, @Bind("mdp") String mdp);
	
	
	
	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 
	
	void close();


}
