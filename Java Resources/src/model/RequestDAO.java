package src.model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import src.controller.DBConnection;
import src.model.SystemAttribute;
import src.interfaccia.UserInterface;

	public class RequestDAO {
		
		public static boolean setWorkingSecretaryState (int idRequest, int workingSecretaryState) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "UPDATE request SET fk_state = ? WHERE id_request = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(2, workingSecretaryState);
		            stmt.setInt(1, idRequest);
		            if (stmt.executeUpdate() > 0) 
				    	 return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			return false;
		}
		
		public static ResultSet getCertificateInfo (String userMail) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT r.id_request, u.name, u.surname, "
			            + "r.hours, r.start_date, r.end_date, r.requested_cfu, r.matricola, "
			            + "r.validated_cfu, a.name AS azienda " + "FROM request r "
			            + "     INNER JOIN azienda a ON r.fk_azienda = a.idazienda "
			            + "     INNER JOIN state s ON r.fk_state = s.id_state "
			            + "     INNER JOIN user u ON r.fk_user = u.email "
			            + "WHERE r.fk_user = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					 stmt.setString(1, userMail);
				     ResultSet res = stmt.executeQuery();
				     if(res.next())
				    	 return  res;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
			return null;
		}
		
		public static int getRequestState (int idRequest) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT fk_state FROM request WHERE id_request = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					 stmt.setInt(1, idRequest);
				     ResultSet res = stmt.executeQuery();
				     if(res.next())
				    	 return  res.getInt("fk_state");
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
			return 0;
		}
		
		public static Request getLastUserRequestPartiallyCompleted (UserInterface user) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT * FROM request WHERE fk_user = ? AND fk_state = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					 stmt.setString(1, user.getEmail());
					 //costante id richiesta parzialmente completata = 1
					 stmt.setInt(2, 1);
				     ResultSet res = stmt.executeQuery();
				     if(res.next())
				    	 return new Request(res.getInt("id_request"),res.getInt("hours"),res.getInt("requested_cfu"),res.getDate("start_date"),res.getDate("end_date"),res.getInt("validated_cfu"),res.getInt("fk_state"),res.getInt("fk_azienda"),res.getString("fk_user"), res.getString("matricola"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public static int findIdByUserAndMiddleState(String userMail){
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = " SELECT id_request FROM request WHERE fk_user = ? AND fk_state != ? AND fk_state != ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, ""+userMail);
					stmt.setString(2, new SystemAttribute().getValueByKey("request-accepted"));
			        stmt.setString(3, new SystemAttribute().getValueByKey("request-refused"));
					ResultSet res = stmt.executeQuery();
					if(res.next())
						return res.getInt("id_request");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return -1;
		}
		
		public static ResultSet findByUserMail(String mail){
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT r.id_request, s.description AS state FROM request r  "
							+ "INNER JOIN state s ON r.fk_state = s.id_state "
							+ "INNER JOIN user u ON r.fk_user = u.email "
							+ "WHERE u.email = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, mail);
					ResultSet res = stmt.executeQuery();
					if(res.next())
						return res;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public static ResultSet findByState(int fk_state){
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT r.id_request, r.matricola, u.name, u.surname, r.hours, "
						+ "r.start_date, r.end_date, r.requested_cfu, r.validated_cfu, "
						+ "a.name AS nomeazienda, s.description FROM request r INNER JOIN user u ON r.fk_user = u.email "
						+ "INNER JOIN azienda a ON fk_azienda = a.idazienda "
						+ "INNER JOIN state s ON fk_state = s.id_state WHERE r.fk_state = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, fk_state);
					ResultSet res = stmt.executeQuery();
					if(res.next())
						return res;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public static synchronized void addCFU(int idrequest) {
			int new_cfu = prelevaCFU(idrequest);
			
			Connection con = new DBConnection().getInstance().getConn();
			String sql = "UPDATE request SET validated_cfu=? WHERE id_request=?";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, new_cfu);
				stmt.setInt(2, idrequest);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public static int prelevaCFU (int idrequest) {
			int r_cfu, v_cfu;
			int new_cfu = 0;
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT requested_cfu, validated_cfu FROM request WHERE id_request=?";
				PreparedStatement stmt;
				try {
					stmt=con.prepareStatement(sql);
					stmt.setInt(1,idrequest);
					ResultSet res = stmt.executeQuery();
					if(res.next()) {
						r_cfu = res.getInt("requested_cfu");
						v_cfu = res.getInt("validated_cfu");
						new_cfu = r_cfu + v_cfu;
						return new_cfu;
					}	
					else
						return 0;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			else {
				System.out.println("ERRORE - Query non eseguita\n");
				return 0;
			}
		}
		
		public static boolean ifExist (int idrequest,String fk_user,int fk_azienda, int fk_state) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT * FROM request WHERE id_request=? AND fk_user = ? AND fk_azienda = ? AND fk_state = ?";
				PreparedStatement stmt;
				try {
					stmt=con.prepareStatement(sql);
					stmt.setInt(1,idrequest);
					stmt.setString(2,fk_user);
					stmt.setInt(3,fk_azienda);
					stmt.setInt(4,fk_state);
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
		
		public static synchronized boolean doSave (Request request) throws ParseException {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "INSERT INTO request(id_request,hours,start_date,end_date,requested_cfu,validated_cfu,fk_user,fk_azienda,fk_state, matricola) VALUES (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmt;
				try {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1,request.getIdRequest());
					stmt.setInt(2,request.getHours());
					stmt.setDate(3,new Date(request.getStartDate().getTime()));
					stmt.setDate(4,new Date(request.getEndDate().getTime()));
					stmt.setInt(5,request.getRequestCfu());
					stmt.setInt(6,request.getValidatedCfu());
					stmt.setString(7,request.getUser());
					stmt.setInt(8,request.getAzienda());
					stmt.setInt(9,request.getState());
					stmt.setString(10, request.getMatricola());
					stmt.executeUpdate();
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("ERRORE - Query non eseguita\n");
				return false;
			}
			return false;
		}
		
		public static synchronized void doSaveOrUpdate (Request request) throws ParseException {
			if(ifExist(request.getIdRequest(),request.getUser(), request.getAzienda(),request.getState() )) {
				Connection con = new DBConnection().getInstance().getConn();
				String sql = "UPDATE request SET  hours=?, start_date=?, end_date=?,requested_cfu,validated_cfu WHERE (id_request=?);";
				PreparedStatement stmt;
				try {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1,request.getHours());
					stmt.setDate(2,new Date(request.getStartDate().getTime()));
					stmt.setDate(3,new Date(request.getEndDate().getTime()));
					stmt.setInt(4,request.getRequestCfu());
					stmt.setInt(5,request.getValidatedCfu());
					stmt.setInt(6,request.getIdRequest());
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				doSave(request);
			}
		}
		
		public static synchronized void doDelete (Request request) {
			if(ifExist(request.getIdRequest(),request.getUser(), request.getAzienda(),request.getState())) {
				Connection con = new DBConnection().getInstance().getConn();
				if(con!=null) {
					String sql = "DELETE FROM request WHERE id_request=?";
					try {
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setInt(1,request.getIdRequest());
						stmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public static synchronized ArrayList <Request> retrieveAll () {
			Connection con = new DBConnection().getInstance().getConn();
			ArrayList <Request> all = new ArrayList<Request>();
			if(con!=null) {
				String sql = "SELECT * FROM request";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet res = stmt.executeQuery();
					while (res.next())  {
						all.add(new Request(res.getInt("id_request"),res.getInt("hours"),res.getInt("requestCfu"),res.getDate("start_date"),res.getDate("end_date"),res.getInt("validated_cfu"),(List<Attached>)res.getObject("attached"),res.getInt("fk_state"),res.getInt("fk_azienda"),res.getString("fk_user")));
					}
					if (all.size()>0) {
						return all;
					}
					else return null;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
			return null;
		}
		
		public static synchronized Request retrieveById (int id_request) {
			Connection con = new DBConnection().getInstance().getConn();
			ArrayList <Request> all = new ArrayList<Request>();
			if(con!=null) {
				String sql = "SELECT * FROM request WHERE id_request=?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1,id_request);
					ResultSet res = stmt.executeQuery();
					if (res.next())
						new Request(res.getInt("id_request"),res.getInt("hours"),res.getInt("requested_cfu"),res.getDate("start_date"),res.getDate("end_date"),res.getInt("validated_cfu"),res.getInt("fk_state"),res.getInt("fk_azienda"),res.getString("fk_user"), res.getString("matricola"));
					
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
		
		public static Request existPendent (String fk_user,int fk_state) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT * FROM request WHERE fk_user = ? AND fk_state = ?";
				PreparedStatement stmt;
				try {
					stmt=con.prepareStatement(sql);
					stmt.setString(1,fk_user);
					stmt.setInt(2,fk_state);
					ResultSet res = stmt.executeQuery();
					if(res.next())
				    	 return new Request(res.getInt("id_request"),res.getInt("hours"),res.getInt("requested_cfu"),res.getDate("start_date"),res.getDate("end_date"),res.getInt("validated_cfu"),res.getInt("fk_state"),res.getInt("fk_azienda"),res.getString("fk_user"), res.getString("matricola"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
			else {
				System.out.println("ERRORE - Query non eseguita\n");
				return null;
			}
		}
		
		public static synchronized Request retrieveByDateAndUser (Request r) {
			Connection con = new DBConnection().getInstance().getConn();
			if(con!=null) {
				String sql = "SELECT * FROM request WHERE start_date=? AND end_date = ? AND fk_user = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setDate(1,new Date(r.getStartDate().getTime()));
					stmt.setDate(2,new Date(r.getEndDate().getTime()));
					stmt.setString(3, r.getUser());
					ResultSet res = stmt.executeQuery();
					if(res.next())
						return new Request(res.getInt("id_request"),res.getInt("hours"),res.getInt("requested_cfu"),res.getDate("start_date"),res.getDate("end_date"),res.getInt("validated_cfu"),res.getInt("fk_state"),res.getInt("fk_azienda"),res.getString("fk_user"), res.getString("matricola") );
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
		


