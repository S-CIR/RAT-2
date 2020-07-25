package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.controller.DBConnection;

public class AttachedDAO {
	
	public static String findNameByRequestId(int requestId) {
		new DBConnection();
		Connection conn = DBConnection.getInstance().getConn();
		String f_name="";
		if (conn != null) {
			String sql = "SELECT filename FROM attached WHERE fk_request = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, requestId);
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					f_name = res.getString("filename");
				}
				return f_name;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static synchronized boolean doSave(Attached a) {
		new DBConnection();
		Connection conn = DBConnection.getInstance().getConn();
		if (conn != null) {
			String sql = "INSERT INTO attached (filename, fk_user, fk_request) VALUES (?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, a.getFilename());
				stmt.setInt(3, a.getFk_request());
				stmt.setString(2, a.getFk_user());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}return false;
	}
	
	private static boolean ifExist (int id, int requestid, String userid) {
		new DBConnection();
		Connection conn = DBConnection.getInstance().getConn();
		
		if (conn != null) {
			String sql = "SELECT * FROM attached WHERE id_attached = ? AND fk_request = ? AND fk_user = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, requestid);
				stmt.setString(3, userid);
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
	
	public static synchronized void doSaveOrUpdate (Attached a) {
		new DBConnection();
		Connection conn = DBConnection.getInstance().getConn();
		if (!ifExist(a.getIdAttached(), a.getFk_request(), a.getFk_user())) {
			doSave(a);
		}
		
		else {
			String sql = "UPDATE attached SET filename = ? WHERE (id_attached = ?)";
			
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, a.getFilename());
				stmt.setInt(2, a.getIdAttached());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static synchronized void doDelete (Attached a) {
		new DBConnection();
		Connection conn = DBConnection.getInstance().getConn();
		if (ifExist(a.getIdAttached(), a.getFk_request(), a.getFk_user())) {
			String sql = "DELETE FROM attached WHERE (id_attached = ?)";
			
			PreparedStatement stmt;
			try {
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, a.getIdAttached());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized ArrayList<Attached> doRetrieveAll (Attached a) {
		Connection conn = new DBConnection().getInstance().getConn();
		ArrayList<Attached> all = new ArrayList<Attached>();
		if (conn != null) {
			String sql = "SELECT * FROM attached";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					all.add(new Attached(res.getInt("id_attached"), res.getString("filename"), res.getInt("fk_request"), res.getString("fk_user")));
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
	
	public static synchronized ArrayList<Attached> doRetrieveById (Attached a) {
		Connection conn = new DBConnection().getInstance().getConn();
		ArrayList<Attached> list = new ArrayList<Attached>();
		if (conn != null) {
			String sql = "SELECT * FROM attached WHERE id_Attached = ?";
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, a.getIdAttached());
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					list.add(new Attached(res.getInt("id_attached"), res.getString("filename"), res.getInt("fk_request"), res.getString("fk_user")));
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
