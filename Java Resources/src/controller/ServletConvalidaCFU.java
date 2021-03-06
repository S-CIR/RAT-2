package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.model.RequestDAO;

@WebServlet("/ServletConvalidaCFU")
public class ServletConvalidaCFU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ServletConvalidaCFU() {
        super();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int req_id = Integer.parseInt(request.getParameter("req_id"));
		RequestDAO.addCFU(req_id);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/ServletRichiesteSecretary?stateId=4");
        requestDispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
