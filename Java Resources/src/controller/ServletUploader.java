package src.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import src.model.Request;


/**
 * Servlet implementation class Uploader.
 */
@WebServlet("/ServletUploader")
public class ServletUploader extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private boolean isMultipart;
  private String filePath;
  private int maxFileSize = 50 * 102400;
  private int maxMemSize = 4 * 1024;
  @SuppressWarnings("unused")
  private File file;

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
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  System.out.println("Sono nell'Uploader");
    Integer result = 0;
    String error = "";
    String content = "";
    Request r= (Request)request.getSession().getAttribute("request");



    filePath = "C:\\Users\\Fra&Lor\\Desktop\\RAT_Files" + r.getIdRequest()+"\\";
    File file = new File(filePath);
    if (!file.exists()) {
      if (!file.mkdir()) {
        result = 0;
        error = "error Creazione Cartella per l'upload dei file";
        System.out.println("error Creazione Cartella per l'upload dei file");
      }
    }
    System.out.println("Cartella creata");
    isMultipart = ServletFileUpload.isMultipartContent(request);
    response.setContentType("text/html");
    if (!isMultipart) {
      result = 0;
      error = "No file uploaded";
      System.out.println("No file uploaded");
    }

    DiskFileItemFactory factory = new DiskFileItemFactory();
    factory.setSizeThreshold(maxMemSize);
    factory.setRepository(new File("c:\\temp"));
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setSizeMax(maxFileSize);

    //-----------------GESTIONE UPLOAD FILES-----------------DA RIVEDERE
    try {
      List fileItems = upload.parseRequest((RequestContext) request);
      Iterator i = fileItems.iterator();

      while (i.hasNext()) {
    	  System.out.println("sono nel ciclo");
        FileItem fi = (FileItem) i.next();
        if (!fi.isFormField()) {
          // Get the uploaded file parameters
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          String fieldName = fi.getFieldName();
          String fileName = timestamp.getTime() + "-" + fi.getName().replaceAll("\\s+", "");
          String contentType = fi.getContentType();
          boolean isInMemory = fi.isInMemory();
          long sizeInBytes = fi.getSize();
          // Write the file
          if (fileName.lastIndexOf("\\") >= 0) {
            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
          } else {
            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
          }
          fi.write(file);
          content += fileName;
          System.out.println(filePath + fileName);
          result = 1;
        }
      }
    } catch (Exception ex) {
      result = 0;
      error = ex.getMessage();
    }



    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    PrintWriter out = response.getWriter();
    out.println(res);
  }

}
