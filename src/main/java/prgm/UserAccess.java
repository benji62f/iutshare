package prgm;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import resources.UserData;


public interface UserAccess {
	@SqlUpdate("insert into UtilisateursData (nom, prenom,pseudo, mdp, type) values (:nom, :prenom, :pseudo, :mdp, :type)")
	@GetGeneratedKeys
	int insert(@Bind("nom") String nom, @Bind("prenom") String prenom, @Bind("pseudo") String pseudo, @Bind("mdp") String mdp, @Bind("type") String type);
	
	@SqlQuery("select * from UtilisateursData where pseudo = :pseudo and mdp = :mdp")
	//@RegisterMapperFactory(RegisterMapperFactory.class)
	UserData verifUser(@Bind("pseudo") String pseudo, @Bind("mdp") String mdp);
	
}
