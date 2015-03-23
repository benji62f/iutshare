import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface UserData {
	
	@SqlUpdate("CREATE TABLE users (login text, mdp text, nom text, prenom text, age integer, formation text, PRIMARY KEY (login), FOREIGN KEY (formation) REFERENCES formations(libelle))")
	void createUserTable();
	
	@SqlUpdate("insert into users (name) values (:name)")
	@GetGeneratedKeys
	String insert(@Bind("login") String login,@Bind("mdp") String mdp,@Bind("nom") String nom,@Bind("prenom") String prenom,@Bind("type") String type);

	@SqlQuery("select * from users where nom = :nom")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("nom") String name);
	
	@SqlUpdate("drop table if exists users")
	void dropUserTable();
	
	
	void close();
}