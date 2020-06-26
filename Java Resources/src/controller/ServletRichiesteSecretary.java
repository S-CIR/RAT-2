package src.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.UserInterface;
import src.model.AttachedDAO;
import src.model.RequestDAO;


@WebServlet("/ServletRichiesteSecretary")
public class ServletRichiesteSecretary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ServletRichiesteSecretary() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = "";
		ResultSet r=null;
		
		try {
        	//-----recupero richieste
        	r = RequestDAO.findByState(1);
            if(r!=null) {
              int count = r.last() ? r.getRow() : 0;
              if (count > 0) {
                r.beforeFirst();
                String classe = "even";
                
                while (r.next()) {
                  int idRequest = r.getInt("id_request");
                  if (classe.equals("odd")) {
                    classe = "even";
                  } else {
                    classe = "odd";
                  }
                  content += "<tr class='" + classe + "' role='row'>";
                  content += "    <td class='text-center'>" + idRequest + "</td>"; //id richiesta
                  content += "    <td class='text-center'>" + r.getString("matricola") + "</td>"; //matricola
                  content += "    <td class='text-center'>" + r.getString("name") + "</td>"; //nome
                  content += "    <td class='text-center'>" + r.getString("surname") + "</td>"; //cognome
                  content += "    <td class='text-center'>" + r.getInt("hours") + "</td>"; //ore
                  content += "    <td class='text-center'>" + r.getInt("requested_cfu") + "</td>"; //cfu richiesti
                  content += "    <td class='text-center'>" + r.getInt("validated_cfu") + "</td>"; //cfu convalidati
                  content += "</tr>";
                }              
              } else {
                content += "<tr>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "<td class=\"text-center\"" + ">Nessuna Richiesta Disponibile</td>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "</tr>";
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }  
	}

}
