package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.controller.DBConnection;
import src.interfaccia.UserInterface;

public class UtenteDAO {
	
	private static boolean ifExist (String mail) {
		Connection con = new DBConnection().getInstance().getConn();
		if(con!=null) {
			String sql = "SELECT * FROM user WHERE user.email=?";
			PreparedStatement stmt;
			try {
				stmt=con.prepareStatement(sql);
				stmt.setString(1,mail);
				ResultSet res = stmt.executeQuery();
				if(res.next())
					return true;
				else
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		else {
			System.out.println("ERRORE - Query non eseguita\n");
			return false;
		}
	}
	
	public static synchronized int doSave (UserInterface user) {
		Connection con = DBConnection.getInstance().getConn();
		if(con!=null) {
			String sql = "INSERT INTO user(email,name,password,surname,gender,user_type) VALUES (?,?,?,?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1,user.getEmail());
				stmt.setString(2,user.getName());
				stmt.setString(3,user.getPassword());
				stmt.setString(4,user.getSurname());
				stmt.setString(5,""+user.getGender());
				stmt.setInt(6,user.getUser_Type());
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		else {
			System.out.println("ERRORE - Query non eseguita\n");
			return 1;	
		}
	}
	
	public static synchronized void doSaveOrUpdate (UserInterface user) {
		if(ifExist(user.getEmail())) {
			Connection con = new DBConnection().getInstance().getConn();
			String sql = "UPDATE user SET name=?, password=?, surname=?, gender=? WHERE (email=?);";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1,user.getName());
				stmt.setString(2,user.getPassword());
				stmt.setString(3,user.getSurname());
				stmt.setString(4,""+user.getGender());
				stmt.setString(5,user.getEmail());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			doSave(user);
		}
	}
	
	public static synchronized void doDelete (UserInterface user) {
		if(ifExist(user.getEmail())) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "DELETE FROM user WHERE user.email=?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1,user.getEmail());
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static synchronized ArrayList <UserInterface> retrieveAll () {
		Connection con = new DBConnection().getInstance().getConn();
		ArrayList <UserInterface> all = new ArrayList<UserInterface>();
		if(con!=null) {
			String sql = "SELECT * FROM user";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					int type = res.getInt("User_type");
					if(type==0) {					//STUDENTE
						all.add(new Student(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type));
					}
					else if(type==1) {				//SEGRETARIO
						all.add(new Secretary(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type));
					}
					else {							//ADMIN
						all.add(new Admin(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type));
					}
				}
				if(all.size()>0)
					return all;
				else {
					System.out.println("Tabella vuota");
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("ERRORE - nell'esecuzione della Query\n");
			return null;
		}
		else {
			System.out.println("ERRORE - Query non eseguita\n");
			return null;
		}	
	}
	
	public static synchronized UserInterface retrieveByEmail (String email) {
		Connection con = new DBConnection().getInstance().getConn();
		UserInterface u=null;
		if(con!=null) {
			String sql = "SELECT * FROM user WHERE email=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1,email);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					int type = res.getInt("user_type");
					if(type==0) {					//STUDENTE
						u = new Student(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
					else if(type==1) {				//SEGRETARIO
						u = new Secretary(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
					else {							//ADMIN
						u = new Admin(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
				}
				if(u != null)
					return u;
				else {
					System.out.println("Utente non trovato");
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("ERRORE - nell'esecuzione della Query\n");
			return null;
		}
		else {
			System.out.println("ERRORE - Query non eseguita\n");
			return null;
		}	
	}
	
	public static synchronized UserInterface verifyLogin (String email, String pw) {
		Connection con = new DBConnection().getInstance().getConn();
		UserInterface u=null;
		if(con!=null) {
			String sql = "SELECT * FROM user WHERE email=? AND password=?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1,email);
				stmt.setString(2,pw);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					int type = res.getInt("user_type");
					if(type==0) {					//STUDENTE
						u = new Student(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
					else if(type==1) {				//SEGRETARIO
						u = new Secretary(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
					else {							//ADMIN
						u = new Admin(res.getString("name"),res.getString("surname"),(res.getString("gender")).charAt(0),res.getString("email"),res.getString("password"),type);
					}
				}
				if(u != null)
					return u;
				else {
					System.out.println("Utente non trovato");
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("ERRORE - nell'esecuzione della Query\n");
			return null;
		}
		else {
			System.out.println("ERRORE - Query non eseguita\n");
			return null;
		}	
	}

}
