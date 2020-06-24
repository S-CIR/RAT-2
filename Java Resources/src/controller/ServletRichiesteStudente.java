package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.interfaccia.UserInterface;
import src.model.AttachedDAO;
import src.model.RequestDAO;

/**
 * Servlet implementation class ServletStudentViewRequest
 */
@WebServlet("/ServletRichiesteStudente")
public class ServletRichiesteStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRichiesteStudente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface u = (UserInterface) request.getSession().getAttribute("user"); 
		String content = "";
		ResultSet r=null, r2=null;
        if (u != null) {
          String email = u.getEmail();
          
          try {
        	//-----recupero richieste
        	r = RequestDAO.findByUserMail(u.getEmail());
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
                  content += "    <td class='text-center'>" + idRequest + "</td>";
                  content += "    <td class='text-center'>";
                  
                  //------recupero allegati
                  r2 = AttachedDAO.findByRequestId(idRequest);
                  if (r2!=null) {
                    int countAttached = r2.last() ? r2.getRow() : 0;
                    int i = 1;
                    if (countAttached > 0) {
                      r2.beforeFirst();
                      while (r2.next()) {
                        if (i == countAttached) {
                          content += "<a href='" + request.getContextPath() + "/Downloader?filename=" + r2.getString("filename") + "&idRequest=" + idRequest + "'>" + r2.getString("filename") + "</a>";
                        } else {
                          content += "<a href='" + request.getContextPath() + "/Downloader?filename=" + r2.getString("filename") + "&idRequest=" + idRequest + "'>" + r2.getString("filename") + "</a>" + " - ";
                        }                        
                        i++;
                      }                      
                    }
                  }
                  
                  content += "    </td>";
                  content += "    <td class='text-center'>" + r.getString("state") + "</td>";
                  content += "</tr>";
                }              
              } else {
                content += "<tr>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "<td class=\"text-center\"" + ">Nessuna Richiesta Presente</td>"
                		+ "<td class=\"text-center\"" + "></td>"
                		+ "</tr>";
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }          
        } else {
        	PrintWriter out=response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Impossibile identificare l'utente, effettua il login');");
			out.println("location='pages/login.jsp';");
			out.println("</script>");
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
