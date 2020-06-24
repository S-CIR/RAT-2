package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;
import src.model.Utils;

import org.json.simple.JSONObject;


/**
 * Servlet implementation class ServletRegistrazioneStudente
 */
@WebServlet("/ServletRegistrazioneStudente")
public class ServletRegistrazioneStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Sono nella servlet");
		
		Integer result = 0;
	    String error = "";
	    String content = "";
	    String redirect = ""; 
	    
	    //-------------------------INIZIO VERIFICHE ---------------------------
	    //verifica nome
	    String nome = request.getParameter("nome");
	    System.out.println(nome);
        if (nome.length() == 0 || nome.length() > 20 || nome.matches(".*\\d+.*")) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        
        //verifica cognome
        String cognome = request.getParameter("cognome");
        System.out.println(cognome);

        if (cognome.length() == 0 || cognome.length() > 20 || cognome.matches(".*\\d+.*")) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        
        
        String email = request.getParameter("email");
        System.out.println(email);
        /* verifica mail
         * l'email è valida se la sua lunghezza è diversa da 0, 
         * se non è presente nel DB e se rispetta il formato
         * se finisce con @studenti.unisa.it
        */
        String prefix = "";
        if (email.length() > 0) {
          prefix = email.substring(0, email.indexOf("@"));
        }
        if (email.length() == 0 
            || !email.endsWith("@studenti.unisa.it") 
            || prefix.length() < 3 || prefix.indexOf(".") == -1) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        
        //verifica sex
        char sex = request.getParameter("sex").charAt(0);
        System.out.println(sex);
        if (sex != 'M' && sex != 'F') {
          throw new IllegalArgumentException("Valore non corretto");
        }

        //verifica la password prima di criptarla
        String pass = request.getParameter("password");
        System.out.println(pass);
        if (pass.length() < 8) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        //cripta password prima di salvarla nel DB
        String password = new Utils().generatePwd(pass);
        
      //-------------------------FINE VERIFICHE ---------------------------
        
        System.out.println("VERIFICHE PASSATE");
        UserInterface s = new Student(nome, cognome, sex,  email, password, 0);
        int i = s.insert();        
        if(i==0) {
        	content = "Registrazione effettuata con successo";
        	redirect = "pages/area_studente/viewRequest.jsp";
            request.getSession().setAttribute("user", s);
        	System.out.println(content);
        	result = 1;
        }else {
        	error = "Impossibile effettuare la registrazione, prova più tardi";
        	redirect = "/index.jsp";
        	System.out.println(error);
        }
        

        JSONObject res = new JSONObject();
        res.put("result", result);
        res.put("error", error);
        res.put("content", content);
        res.put("redirect", redirect);
        PrintWriter out = response.getWriter();
        response.setContentType("json"); 
        out.println(res);
              
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
