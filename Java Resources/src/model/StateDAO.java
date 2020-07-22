package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.controller.DBConnection;

public class StateDAO {
	
	private static boolean ifExist (int id) {
		
		Connection conn = new DBConnection().getInstance().getConn();
		
		if (conn != null) {
			String sql = "SELECT * FROM state WHERE id_state = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet res = stmt.executeQuery();
				if (res.next()) return true;
				else return false;	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
		}
	return false;
	}
	
	public static synchronized void doSave (State s) {
		
		Connection conn = new DBConnection().getInstance().getConn();
		
		if (conn != null) {
			String sql = "INSERT INTO state (id_state, description) VALUES (?,?)";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, s.getId_State());
				stmt.setString(2, s.getDescription());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void doSaveOrUpdate (State s) {
		
		Connection conn = new DBConnection().getInstance().getConn();
		if (!ifExist(s.getId_State())) {
			doSave(s);
		}
		else {
			String sql = "UPDATE state SET description = ? WHERE (id_state = ?)";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, s.getDescription());
				stmt.setInt(2, s.getId_State());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void doDelete (State s) {
		Connection conn = new DBConnection().getInstance().getConn();
		if (ifExist(s.getId_State())) {
			String sql = "DELETE FROM state WHERE (id_state = ?)";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, s.getId_State());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized ArrayList<State> doRetrieveAll (State s) {
		Connection conn = new DBConnection().getInstance().getConn();
		ArrayList<State> all = new ArrayList<State>();
		if (conn != null) {
			String sql = "SELECT * FROM state";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					all.add(new State(res.getInt("id_state"), res.getString("description")));
				}
				if (all.size()>0) {
					return all;
				}
				else return null;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
		}
	return null;
	}
	
	public static synchronized ArrayList<State> doRetrieveById (State s) {
		Connection conn = new DBConnection().getInstance().getConn();
		ArrayList<State> list = new ArrayList<State>();
		if (conn != null) {
			String sql = "SELECT * FROM state WHERE id_state = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, s.getId_State());
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					list.add(new State(res.getInt("id_state"), res.getString("description")));
				}
				if (list.size()>0) {
					return list;
				}
				else return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
		}
	return null;
	}

}
