package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.*;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err ="";
		ArrayList<String> errs= new ArrayList<String>(), vars = new ArrayList<String>();
		String redirect = ""; 
		  
		//---------------INIZIO VERIFICHE---------------
		  
		//verifica year
		String year = request.getParameter("year");
	    System.out.println(year);
	    try {
	    	validate(year, "year", "year");
	    	vars.add(0,year);
	    }catch(IllegalArgumentException e) {		    	
	    	System.out.println("errore year");
	    	errs.add("year");
	    	vars.add(0,"");
	    }
	    
	  //verifica matricola
	  		String matricola = request.getParameter("matricola");;
	  	    System.out.println(matricola);
	  	    try {
	  	    	validate(matricola, "matricola", "matricola");
	  	    	vars.add(0,matricola);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore matricola");
	  	    	errs.add("matricola");
	  	    	vars.add(1,"");
	  	    }
		
       //verifica hours
	  	    String hours = request.getParameter("hours");
	  	    System.out.println(hours);
	  	    try {
	  	    	validate(hours, "hours", "hours");
	  	    	vars.add(0,hours);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore hours");
	  	    	errs.add("hours");
	  	    	vars.add(2,"");
	  	    }
        
	  	//verifica azienda
	  	    String idAzienda = request.getParameter("azienda");
	  	    System.out.println(idAzienda);
	  	    try {
	  	    	validate(idAzienda, "azienda", "azienda");
	  	    	vars.add(0,idAzienda);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore azienda");
	  	    	errs.add("azienda");
	  	    	vars.add(3,"");
	  	    }
	  	    
	  	//verifica endDate
	  	    String endDate = request.getParameter("endDate");
	  	    System.out.println(endDate);
	  	    try {
	  	    	validate(endDate, "endDate", "endDate");
	  	    	vars.add(0,endDate);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore endDate");
	  	    	errs.add("endDate");
	  	    	vars.add(4,"");
	  	    }
	  	    
	  	//verifica startDate
	  	    String startDate = request.getParameter("startDate");
	  	    System.out.println(startDate);
	  	    try {
	  	    	validate(startDate, "startDate", "startDate");
	  	    	vars.add(0,startDate);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore startDate");
	  	    	errs.add("startDate");
	  	    	vars.add(5,"");
	  	    }
	  	    
	  	//verifica cfu
	  	    String requestedCfu = request.getParameter("cfu");
	  	    System.out.println(requestedCfu);
	  	    try {
	  	    	validate(requestedCfu, "cfu", "cfu");
	  	    	vars.add(0,requestedCfu);
	  	    }catch(IllegalArgumentException e) {		    	
	  	    	System.out.println("errore cfu");
	  	    	errs.add("cfu");
	  	    	vars.add(6,"");
	  	    }
        
        //-----------FINE VERIFICHE
	  	  if(errs.size()==0) {
		        System.out.println("VERIFICHE PASSATE");
		        
		        src.interfaccia.UserInterface user = (src.interfaccia.UserInterface) request.getSession().getAttribute("user");
		        int validatedCfu = 0;
		        String idUser = user.getEmail();
		        int idState =1;
		        
		        int requestId = RequestDAO.findIdByUserAndMiddleState(idUser);
		        
		        if(requestId ==-1 ) {
		        	  //non ci sono richieste pendenti quindi inserisco una nuova richiesta
		        	  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
		        	  Date d1;
					try {
						d1 = formatter.parse(startDate);
						Date d2=formatter.parse(endDate);
			        	  Request r= new Request(Integer.parseInt(hours), Integer.parseInt(requestedCfu), d1, d2, validatedCfu, idState, Integer.parseInt(idAzienda) , idUser, matricola);
			        	  System.out.println(r.toString());
			        	  if(RequestDAO.doSave(r)) {
			        		  Request rf = RequestDAO.getLastUserRequestPartiallyCompleted((UserInterface) request.getSession().getAttribute("user"));
			        		  request.setAttribute("request", rf);
			        		  redirect = "/pages/area_studente/uploadAttached.jsp";
			        	  } else {//ci sono richieste pendenti
				        	  err = "Una richiesta gi&agrave; presentata non &egrave; stata ancora conclusa.";
				        	  redirect = "/form.jsp";    
				        	  System.out.println(err);
				        } 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	  
		        }
		        
		        RequestDispatcher rd=request.getRequestDispatcher(redirect);
		    	rd.forward(request, response);
	        }else {
	        	reloadForm(response, vars, errs);
	        }
	  	    
	}	  	    
        
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static void validate(String var, String type,String input) throws IllegalArgumentException {
		switch(type) {
			case "year":
				if(var.length() == 0)
			        throw new IllegalArgumentException("Formato "+input+" non corretto");
				break;
			case "matricola":
			     if (var.length() != 10) 
			        throw new IllegalArgumentException("Formato "+input+" non corretto");
			     break;
			case "hours":
				 if (Integer.parseInt(var)<150) 
			          throw new IllegalArgumentException("Valore "+input+" non corretto");
			     break;
			case "azienda":
				 if (Integer.parseInt(var)==0) 
			          throw new IllegalArgumentException("Valore "+input+" non corretto");
			     break;
			case "startDate":
				 if (var.length()==0) 
			          throw new IllegalArgumentException("Valore "+input+" non corretto");
			     break;     
			case "endDate":
				 if (var.length()==0) 
			          throw new IllegalArgumentException("Valore "+input+" non corretto");
			     break;      
			case "cfu":
				if (Integer.parseInt(var)!=6 && Integer.parseInt(var)!=12) 
			          throw new IllegalArgumentException("Formato "+input+" non corretto");
			    break;
					
		}
	}
	
	private static void reloadForm(HttpServletResponse response,ArrayList<String> vars, ArrayList<String> errs ) {		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			String s = "alert('Formato non corretto:";
			for(int i=0;i<errs.size();i++) {
				s+=(" "+errs.get(i)+" ");
			}
			s+=("');");
			out.println(s);			
			out.println("location='pages/area_studente/form.jsp';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
