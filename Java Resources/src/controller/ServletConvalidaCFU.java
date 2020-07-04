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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int req_id = (int) request.getAttribute("req_id");
		RequestDAO.addCFU(req_id);
		
		RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/pages/area_secretary/viewRequest.jsp");
        requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
