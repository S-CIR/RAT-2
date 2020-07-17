package src.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.UserInterface;
import src.model.AttachedDAO;
import src.model.RequestDAO;


@WebServlet("/ServletRichiesteSecretary")
public class ServletRichiesteSecretary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ServletRichiesteSecretary() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int stateId = Integer.parseInt(request.getParameter("stateId"));
		int count = 0;
		ResultSet r=null;
		int req_ids[] = new int[10];
		String matricole[] = new String[10];
		String nomi[] = new String[10];
		String cognomi[] = new String[10];
		int hours[] = new int[10];
		int req_cfu[] = new int[10];
		int val_cfu[] = new int[10];
		String state_desc[] = new String[10];
		
		try {
        	r = RequestDAO.findByState(stateId);	//Recupero richieste in un determinato stato
        	
            if(r!=null) {
            	  int i = 0;
            	  r.beforeFirst();
            	  while (r.next()) {
  	                  req_ids[i]=r.getInt("id_request");
  	                  matricole[i] = r.getString("matricola");
  	                  nomi[i] = r.getString("name");
  	                  cognomi[i] = r.getString("surname");
  	                  hours[i] = r.getInt("hours");
  	                  req_cfu[i] = r.getInt("requested_cfu");
  	                  val_cfu[i] = r.getInt("validated_cfu");
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
		request.setAttribute("req_cfu", req_cfu);
		request.setAttribute("val_cfu", val_cfu);
		request.setAttribute("state_desc", state_desc);
        request.setAttribute("req_num", count);
        
        RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/pages/area_secretary/viewRequest.jsp");
        requestDispatcher.forward(request, response);
	}

}
