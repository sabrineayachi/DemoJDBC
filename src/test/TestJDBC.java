package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author <a href="mailto:sabrinee.ayachi@gmail.com">Sabrine AYACHI</a>
 */
public class TestJDBC  {
	
	Connection con = null;
	static String url = "jdbc:mysql://localhost:3306/mabase";
	static String user = "root";
	static String pwd = "";

	// static bloc
	static {
		Properties prop = new Properties();
		try {
			prop.load(TestJDBC.class.getResourceAsStream("/Config.properties"));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			pwd = prop.getProperty("pwd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connecter(){
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			if (con != null)
				System.out.println("connexion effectu�e");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ajouter(){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute("insert into Client(idclient, nom, prenom, adresse) values(586, 'abr', 'julie', null)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void afficher(){
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("select * from Client");
			while(res.next())
			{ int id = res.getInt("idclient");
			  String nom = res.getString("nom");
			  String prenom = res.getString("prenom");
			  System.out.println(" nom = " + nom + " id = " + id + " prenom " + prenom);
			
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void supprimer(int id){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute("delete from Client where idclient = " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void update(int id, String nom){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute("update Client set nom = '" + nom + "' where idclient = " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		TestJDBC j = new TestJDBC();
		j.connecter();
		//j.ajouter();
		j.afficher();
		//j.supprimer(586);
		System.out.println("/////////////////////");
		//j.afficher();
		System.out.println("*******************");
		j.update(1, "abc");
		j.afficher();
	}

}
