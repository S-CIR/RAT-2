package src.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import src.interfaccia.UserInterface;
import src.model.Attached;
import src.model.AttachedDAO;
import src.model.Request;
import src.model.RequestDAO;
import src.model.SystemAttribute;

/**
 * Servlet implementation class ServletInsertAttachedStudent
 */
@WebServlet("/ServletInsertAttachedStudent")
public class ServletInsertAttachedStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertAttachedStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = 1;
		String error = "";
		String content = "";
		String redirect = "";
		System.out.println("Sono nella servlet insertAttached");
				 // inserimento allegati in DB
        String[] filenames = request.getParameterValues("filenames[]");
        if (filenames.length != 2 
            || !filenames[0].endsWith(".pdf") 
            || !filenames[1].endsWith(".pdf")) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        Request r = (Request) request.getSession().getAttribute("request");
        UserInterface user = (UserInterface) request.getSession().getAttribute("user");
        
        for (int i = 0; i < filenames.length; i++) {
        	  Attached a = new Attached(r.getIdRequest(), filenames[i], user.getEmail());
        	  if(!AttachedDAO.doSave(a)) result=0;
        }

        if (result == 1) {
        	//stato richiesta di valutazione della segreteria = 2
            if(RequestDAO.setWorkingSecretaryState(r.getIdRequest(), 2)) {
                 redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
                 content = "Allegati inseriti con successo.";
            }else {
            	error += " Impossibile cambiare stato alla richiesta.";
                result = 0;
            }
          }
        
        JSONObject res = new JSONObject();
        res.put("result", result);
        res.put("error", error);
        res.put("content", content);
        res.put("redirect", redirect);
        PrintWriter out = response.getWriter();
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
