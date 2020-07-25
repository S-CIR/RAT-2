package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.model.RequestDAO;

/**
 * Servlet implementation class ServletInoltro
 */
@WebServlet("/ServletInoltro")
public class ServletInoltro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ServletInoltro() {
        super();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int req_id = Integer.parseInt(request.getParameter("req_id"));
		int next_state = Integer.parseInt(request.getParameter("next_state"));
		int user_id = Integer.parseInt(request.getParameter("user_id")); //tipo di utente (1=segreteria, 2=admin)
		
		RequestDispatcher requestDispatcher= null;
		RequestDAO.inoltra(req_id, next_state);
		if(user_id == 1) {	//segreteria
			requestDispatcher=request.getRequestDispatcher("/ServletRichiesteSecretary?stateId=2");
		}
		else if(user_id == 2) {	//admin
			requestDispatcher=request.getRequestDispatcher("/ServletRichiesteAdmin");
		}
        requestDispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
