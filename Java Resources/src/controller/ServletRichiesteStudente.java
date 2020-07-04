package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class ServletStudentViewRequest
 */
@WebServlet("/ServletRichiesteStudente")
public class ServletRichiesteStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRichiesteStudente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface u = (UserInterface) request.getSession().getAttribute("user"); 
		String content = "";
		int error = 0;
		int count=0;
		int req_ids[] = new int[2];
		String req_states[]= new String[2];
		String req_filenames[] = new String[2];
		ResultSet r=null, r2=null;
        if (u != null) {
          String email = u.getEmail();    
          try {
        	r = RequestDAO.findByUserMail(u.getEmail());
        	if(r!=null) {
        		int i = 0;
        		r.beforeFirst();
        		while (r.next()) {
                    System.out.println(r.getInt("id_request")+" "+r.getString("state"));
                    if(!r.getString("state").equals("Parzialmente Completata")) {
	                    req_ids[i]=r.getInt("id_request");
	                    req_states[i]=r.getString("state");
	                    i++;
                    }
        		}
        		count = i;
        	}
        	for(int i = 0; i<count;i++) {
        		String temp = AttachedDAO.findNameByRequestId(req_ids[i]);
        		if (temp!=null) {
            		req_filenames[i]= temp;  
            		System.out.println(req_filenames[0]);
        		}
        	}
          } catch (SQLException e) {
				e.printStackTrace();
			
          }
          request.setAttribute("req_ids", req_ids);
          request.setAttribute("req_states", req_states);
          request.setAttribute("req_filenames", req_filenames);
          request.setAttribute("req_num", count);
          RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/pages/area_studente/viewRequest.jsp");
          requestDispatcher.forward(request, response);
        }else {
        	PrintWriter out=response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Impossibile identificare l'utente, effettua il login');");
			out.println("location='pages/login.jsp';");
			out.println("</script>");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
