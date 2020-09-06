package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.controller.DBConnection;

public class AziendaDAO {
	
	public static boolean ifExist(int id) {
		Connection con= new DBConnection().getInstance().getConn();
		if(con!=null) {
			String sql="SELECT * FROM azienda WHERE idazienda=?";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1,id);
				ResultSet res=stmt.executeQuery();
				if(res.next())
					return true;
				else
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			return false;
		}else {
			System.out.println("Connessione non effettuata");
			return false;
		}			
	}
	
	
	public static synchronized void doSave(Azienda a) {
		
		Connection con= new DBConnection().getInstance().getConn();
		if(con!=null) {
			String sql="INSERT INTO azienda (idazienda,email,name,site) VALUES(?,?,?,?)";
			try {
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1,a.getId());
				stmt.setString(2,a.getEmail());
				stmt.setString(3,a.getName());
				stmt.setString(4,a.getSite());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Connessione non effettuata");
	}
	
	public static synchronized void doSaveOrUpdate(Azienda a) {
		Connection con= new DBConnection().getInstance().getConn();
		if(con!=null) {
			if(ifExist(a.getId())) {
				String sql="UPDATE azienda SET  email = ?, name = ?, site = ? WHERE (idazienda = ?)";
				try {
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt(4,a.getId());
					stmt.setString(1,a.getEmail());
					stmt.setString(2,a.getName());
					stmt.setString(3,a.getSite());
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}else {
				doSave(a);
			}
		}	
	}
	
	public static synchronized void doDelete(Azienda a) {
		Connection con= new DBConnection().getInstance().getConn();
		if(con!=null) {
			if(ifExist(a.getId())) {
				String sql="DELETE FROM azienda WHERE (idazienda= ?)";
				try {
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setInt(1,a.getId());
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				doSave(a);
			}
		}
	}
	
	public static synchronized ArrayList<Azienda> doRetrieveAll() {
		Connection con= new DBConnection().getInstance().getConn();
		ArrayList<Azienda> all= new ArrayList<Azienda>();
		if(con!=null) {
			String sql="SELECT * FROM azienda";
			try {
				PreparedStatement stmt=con.prepareStatement(sql);
				ResultSet res=stmt.executeQuery();
				while(res.next()) {
					all.add(new Azienda(res.getInt("idazienda"),res.getString("email"),res.getString("name"),res.getString("site")));
				}
				if (all.size()>0)
					return all;
				else 
					return null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static synchronized ArrayList<Azienda> doRetrieveById(int id){
		Connection con= new DBConnection().getInstance().getConn();
		ArrayList<Azienda> al= new ArrayList<Azienda>();
		if(con!=null) {
			String sql="SELECT * FROM azienda WHERE idazienda=?";
			try {
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1,id);
				ResultSet res=stmt.executeQuery();
				while(res.next()) {
					al.add(new Azienda(res.getInt("idazienda"),res.getString("email"),res.getString("name"),res.getString("site")));
				}
				if (al.size()>0)
					return al;
				else 
					return null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public static synchronized ArrayList<Azienda> doRetrieveByState(int state){
		Connection con= new DBConnection().getInstance().getConn();
		ArrayList<Azienda> al= new ArrayList<Azienda>();
		if(con!=null) {
			String sql="SELECT * FROM azienda WHERE state=?";
			try {
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1,state);
				ResultSet res=stmt.executeQuery();
				while(res.next()) {
					al.add(new Azienda(res.getInt("idazienda"),res.getString("email"),res.getString("name"),res.getString("site")));
				}
				if (al.size()>0)
					return al;
				else 
					return null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}	
		return null;
	}

}
