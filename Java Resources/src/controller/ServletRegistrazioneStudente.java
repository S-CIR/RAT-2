package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;
import src.controller.Utils;
<<<<<<< HEAD

=======
>>>>>>> master

/**
 * Servlet implementation class ServletRegistrazioneStudente
 */
@WebServlet("/ServletRegistrazioneStudente")
public class ServletRegistrazioneStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> vars = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrazioneStudente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Sono nella servlet");
		
		Integer result = 0;
	    String error = "";
	    String content = "";
	    String redirect = ""; 
<<<<<<< HEAD
	    ArrayList<String> vars = new ArrayList<String>(), errs= new ArrayList<String>();
=======
	    ArrayList<String> errs= new ArrayList<String>();
>>>>>>> master
	    
	    //-------------------------INIZIO VERIFICHE ---------------------------
	    //verifica nome
	    String nome = request.getParameter("nome");
	    System.out.println(nome);
	    try {
	    	validate(nome, "testo", "nome");
	    	vars.add(0,nome);
	    }catch(IllegalArgumentException e) {		    	
	    	System.out.println("errore nome");
	    	errs.add("nome");
	    	vars.add(0,"");
	    }
        //verifica cognome
        String cognome = request.getParameter("cognome");
        System.out.println(cognome);
        try {
	    	validate(cognome, "testo", "cognome");
	    	vars.add(1,cognome);
	    }catch(IllegalArgumentException e) {		    	
	    	System.out.println("errore cognome");
	    	errs.add("cognome");
	    	vars.add(1,"");
	    }

        String email = request.getParameter("email");
        System.out.println(email);
        /* verifica mail
         * l'email è valida se la sua lunghezza è diversa da 0, 
         * se non è presente nel DB e se rispetta il formato
         * se finisce con @studenti.unisa.it
        */
        try {
	    	validate(email, "mail", "mail");
	    	vars.add(2,email);
	    }catch(IllegalArgumentException e) {
	    	errs.add("mail");
	    	vars.add(2,"");
	    }
<<<<<<< HEAD
       
        
=======

>>>>>>> master
        //verifica sex
        char sex = request.getParameter("sex").charAt(0);
        System.out.println(sex);
        try {
	    	validate(""+sex, "sex", "sex");
	    	vars.add(3,""+sex);
	    }catch(IllegalArgumentException e) {		    	
	    	errs.add("sex");
	    	vars.add(3,"");
	    }
        //verifica la password prima di criptarla, deve contenere un numero, un carattere minuscolo, 
        //uno maiuscolo e deve avere lunghezza min 8 e max 20
        String pass = request.getParameter("password");
        String ver = request.getParameter("verifyPassword");
<<<<<<< HEAD
=======
        System.out.println("!!"+ver);
        System.out.println(pass);
>>>>>>> master
        if(pass.equals(ver)) {
	        try {
		    	validate(pass, "pass", "password");
		    	vars.add(4,pass);
		    }catch(IllegalArgumentException e) {		    	
		    	System.out.println("errore password");
		    	errs.add("pass");
		    	vars.add(4,"");
		    }
<<<<<<< HEAD
        }else {
        	errs.add("verPass");
=======
        }
        else {
        	System.out.println("errore verifica password");
			errs.add("verifica password");
>>>>>>> master
        }
        
        //cripta password prima di salvarla nel DB
        String password = new Utils().generatePwd(pass);
        
      //-------------------------FINE VERIFICHE ---------------------------
        if(errs.size()==0) {
	        System.out.println("VERIFICHE PASSATE");
	        UserInterface s = new Student(nome, cognome, sex,  email, password, 0);
	        int i = s.insert();        
	        if(i==0) {
	        	content = "Registrazione effettuata con successo";
	        	redirect = "pages/login.jsp";
	            request.getSession().setAttribute("user", s);
	        	System.out.println(content);
	        	result = 1;
	        }else if(i!=0){
	        	error = "Impossibile effettuare la registrazione, prova più tardi";
	        	redirect = "/index.jsp";
	        	System.out.println(error);
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
			case "testo":
				if(var.length() == 0 || var.length() > 20 || !var.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$"))
			        throw new IllegalArgumentException("Formato "+input+" non corretto");
				break;
			case "mail":
				 String prefix = "";
				 boolean exist = false;
			     if (var.length() > 0) {
			       prefix = var.substring(0, var.indexOf("@"));
			       exist=UtenteDAO.ifExist(var);
			     }
			     if (var.length() == 0 || exist
			        || !var.endsWith("@studenti.unisa.it") 
			        || prefix.length() < 3 || prefix.indexOf(".") == -1) {
			        throw new IllegalArgumentException("Formato "+input+" non corretto");
			     }
			     break;
			case "sex":
				 if (!var.equals("M") && !var.equals("F")) 
			          throw new IllegalArgumentException("Valore "+input+" non corretto");
			     break;
			case "pass":
				if (var.length() < 8 || !var.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$")) 
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
			out.println("location='pages/area_studente/signUp.jsp';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

