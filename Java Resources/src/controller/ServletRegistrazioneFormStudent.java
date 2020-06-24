package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.*;
import src.model.SystemAttribute;
import src.model.Request;
import src.model.RequestDAO;

/**
 * Servlet implementation class ServletRegistrazioneFormStudent
 */
@WebServlet("/ServletRegistrazioneFormStudent")
public class ServletRegistrazioneFormStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrazioneFormStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err="";
		//---------------Verifiche
        //Anno
        String year = request.getParameter("year");
        if (year.length() == 0) {
          throw new IllegalArgumentException("Anno di immatricolazione non corretto");
        }
        //Matricola
        String matricola = request.getParameter("matricola");
        int length = matricola.length();						
        if (length !=10) {
          throw new IllegalArgumentException("Valore matricola non corretto");
        }
        //Ore
        int hours = Integer.parseInt(request.getParameter("hours"));
        if (hours<150) {
          throw new IllegalArgumentException("Valore ore effettuate non corretto");
        }
        //Azienda
        int idAzienda = Integer.parseInt(request.getParameter("azienda"));
        if (idAzienda == 0) {
          throw new IllegalArgumentException("Valore azienda non corretto");
        }
        //Fine periodo
        String endDate = request.getParameter("endDate");
        if (endDate.length() == 0) {
          throw new IllegalArgumentException("Data di fine tirocinio non corretta");
        }
        //Inizio periodo
        String startDate = request.getParameter("startDate");
        if (startDate.length() == 0) {
          throw new IllegalArgumentException("Data di inizio tirocinio non corretta");
        }
       //cfu
        int requestedCfu = Integer.parseInt(request.getParameter("cfu"));
        if (requestedCfu!=6 && requestedCfu != 12) {
          throw new IllegalArgumentException("Valore di CFU richiesti non corretto");
        }
        
        //-----------FINE VERIFICHE
        
        
        src.interfaccia.UserInterface user = (src.interfaccia.UserInterface) request.getSession().getAttribute("user");
        int validatedCfu = 0;
        String idUser = user.getEmail();
        
        int idState =1; //richiesta parzialmente completata
        
        try {
        	
          int requestId = RequestDAO.findIdByUserAndMiddleState(idUser); //r
          System.out.println(requestId);
          if(requestId ==-1 ) {
        	  //non ci sono richieste pendenti quindi inserisco una nuova richiesta
        	  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
        	  Date d1=formatter.parse(startDate);
        	  Date d2=formatter.parse(endDate);
        	  Request r= new Request(hours, requestedCfu, d1, d2, validatedCfu, idState, idAzienda, idUser, matricola);
        	  System.out.println(r.toString());
        	  if(RequestDAO.doSave(r)) {
        		  Request rf = RequestDAO.retrieveByDateAndUser(r);
        		  request.setAttribute("request", rf);
        		  RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/area_studente/uploadAttached.jsp");
 				  rd.forward(request, response);
        	  }

          } else {//ci sono richieste pendenti
              err = "Una richiesta gi&agrave; presentata non &egrave; stata ancora conclusa.";
              //alert richieste pendenti ->uploadAttached
              PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Esiste già una richiesta non completata, eseguo il reindirizzamento.');");
				out.println("<%");
				out.println("Request rf = RequestDAO.retrieveById(requestId);");
				out.println("request.setAttribute(\"request\", rf);");
				out.println("%>");
				out.println("location='/pages/area_studente/uploadAttached.jsp;");
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
