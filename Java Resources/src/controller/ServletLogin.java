package src.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import src.model.UtenteDAO;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String redirect="";
		 String email = request.getParameter("email");
	     String password = new src.model.Utils().generatePwd(request.getParameter("password"));
	     RequestDispatcher requestDispatcher;
	     try {
	        src.interfaccia.UserInterface u = UtenteDAO.verifyLogin(email, password);
	        if (u!=null) {
	        	System.out.println("Login effetuato");
	        	src.interfaccia.UserInterface user = null;
	        	int type = u.getUser_Type();
	        	if (type == 0) { // Profilo Studente
	                redirect ="/ServletRichiesteStudente";
	                user = u;
	              } else if (type == 1) { // Profilo Secretary
	                redirect = request.getContextPath() + "/pages/area_secretary/secretaryHome.jsp";
	                user = u;
	              } else if (type == 2) { // Profilo Admin
	                redirect ="/ServletRichiesteAdmin";
	                user = u;
	              }
	        	 request.getSession().setAttribute("user", user);
	        	 //reindirizzamento sulla home relativa alla tipologia di utente
	        	 requestDispatcher=getServletContext().getRequestDispatcher(redirect);
				 requestDispatcher.forward(request, response);
	        }else {
	        		
	                PrintWriter out=response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('I dati inseriti non sono corretti, riprova o registrati.');");
					out.println("location='pages/login.jsp';");
					out.println("</script>");
	            }
	     } catch (Exception e) {
	    	 e.printStackTrace();
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
