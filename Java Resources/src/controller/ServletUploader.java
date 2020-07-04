package src.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import src.model.Attached;
import src.model.AttachedDAO;
import src.model.Request;
import src.model.RequestDAO;


/**
 * Servlet implementation class Uploader.
 */
@WebServlet("/ServletUploader")
@MultipartConfig
public class ServletUploader extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * constructor.
   * @see HttpServlet#HttpServlet()
   */
  public ServletUploader() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * method doGet.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * method doPost.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings({"unchecked", "unused", "rawtypes"})
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Sono nell'Uploader");
    Integer result = 0;
    String error = "";
    String content = "";
    String red="";
    Request r= (Request)request.getSession().getAttribute("request");
    String matricola = request.getParameter("matricola");
    String sd = request.getParameter("startDate");
    String ed = request.getParameter("endDate");
    int idReq = Integer.parseInt(request.getParameter("idRequest"));
    String mail = request.getParameter("mail");
    
    Part filePart = request.getPart("file"); 
    String fileName = matricola+"_"+sd+"_"+ed+".pdf";
    InputStream fileContent = filePart.getInputStream();
    
    File uploads = new File("C:\\Users\\Carmine\\Desktop\\RAT_Files\\");
    File file = new File(uploads, fileName);

    try (InputStream input = fileContent) {
        Files.copy(input, file.toPath());
        result = 1;
        content="Caricamento riuscito";
        //caricamento info del file su DB
        AttachedDAO.doSave(new Attached(idReq, fileName, mail));
        //settare fk_state della request a 2 (segreteria)
        RequestDAO.setWorkingSecretaryState(idReq);
        //-------------------------
        
        red = "/ServletRichiesteStudente";
    } catch (Exception ex) {
        result = 0;
        error = "Impossibile salvare il file";
        red = "pages/area_studente/uploadAttached.jsp";
    }
    
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	pw.println("<script type=\"text/javascript\">");
	pw.println("alert('"+error+content+"');");
	pw.println("</script>");
	RequestDispatcher rd=request.getRequestDispatcher(red);
	rd.include(request, response);
    


  }

}
