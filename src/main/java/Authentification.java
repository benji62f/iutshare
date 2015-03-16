import java.sql.*;

public class Authentification
{
  public static void main(String args[]) throws Exception
  {
      Connection con=null;
      Statement stmt;
      
      Class.forName("org.postgresql.Driver"); 
      
      

      String url = "jdbc:postgresql://psqlserv/n3p1";
      String nom = "admin";
      String mdp = "xxx";
      con = DriverManager.getConnection(url,nom,mdp);
      stmt = con.createStatement();
      String query = "select id , nom , prenom from personne";
      ResultSet rs = stmt.executeQuery(query);
          
      System.out.println("Liste des personnes:");
      while (rs.next()) 
      {
              int id = rs.getInt("id"); // id
              String p = rs.getString("prenom"); // prenom
              String n = rs.getString("nom");       // nom
              System.out.println(id+ " " + p + " " + n);
      }
      con.close();
  } 
}
