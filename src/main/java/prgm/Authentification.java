package prgm;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/servlet/Authentification")
public class Authentification extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Connection con;
			Statement stmt;
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:database.db");
			stmt = con.createStatement();
			if (stmt == null) {
				throw new Exception("Statement null");
			}	
			PreparedStatement ps =con.prepareStatement("SELECT * FROM utilisateurs WHERE login=? and mdp=?");
			ps.setString(1,req.getParameter("login"));
			ps.setString(2,req.getParameter("mdp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
