package src.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.model.RequestDAO;

/**
 * Servlet implementation class ServletRichiesteAdmin
 */
@WebServlet("/ServletRichiesteAdmin")
public class ServletRichiesteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ServletRichiesteAdmin() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;
		ResultSet r=null;
		int req_ids[] = new int[10];
		String matricole[] = new String[10];
		String nomi[] = new String[10];
		String cognomi[] = new String[10];
		int hours[] = new int[10];
		Date s_dates[] = new Date[10];
		Date e_dates[] = new Date[10];
		int req_cfu[] = new int[10];
		int val_cfu[] = new int[10];
		String aziende[] = new String[10];
		String state_desc[] = new String[10];
		
		try {
        	r = RequestDAO.findByState(3);	//Recupero richieste in un determinato stato
        	
            if(r!=null) {
            	  int i = 0;
            	  r.beforeFirst();
            	  while (r.next()) {
  	                  req_ids[i]=r.getInt("id_request");
  	                  matricole[i] = r.getString("matricola");
  	                  nomi[i] = r.getString("name");
  	                  cognomi[i] = r.getString("surname");
  	                  hours[i] = r.getInt("hours");
  	                  s_dates[i] = r.getDate("start_date");
  	                  e_dates[i] = r.getDate("end_date");
  	                  req_cfu[i] = r.getInt("requested_cfu");
  	                  val_cfu[i] = r.getInt("validated_cfu");
  	                  aziende[i] = r.getString("nomeazienda");
  	                  state_desc[i] = r.getString("description");
  	                  i++;
            	  }
            	  count = i;
              }
           }
		
		catch (SQLException e) {
            e.printStackTrace();
          }
		
		request.setAttribute("req_ids", req_ids);
		request.setAttribute("matricole", matricole);
		request.setAttribute("nomi", nomi);
		request.setAttribute("cognomi", cognomi);
		request.setAttribute("hours", hours);
		request.setAttribute("s_dates", s_dates);
		request.setAttribute("e_dates", e_dates);
		request.setAttribute("req_cfu", req_cfu);
		request.setAttribute("val_cfu", val_cfu);
		request.setAttribute("aziende", aziende);
        request.setAttribute("state_desc", state_desc);
        request.setAttribute("req_num", count);
        
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/pages/area_admin/viewRequest.jsp");
        requestDispatcher.forward(request, response);
	}

}
