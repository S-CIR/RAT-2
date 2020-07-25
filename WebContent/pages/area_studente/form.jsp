<%@page import="src.model.Request"%>
<%@page import="src.model.RequestDAO"%>
<%@page import="src.interfaccia.UserInterface"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="src.model.Azienda, java.util.ArrayList, src.model.AziendaDAO, src.model.Student, src.controller.CheckSession, src.model.SystemAttribute, java.text.SimpleDateFormat, java.time.*, src.controller.DBConnection, java.sql.Connection, java.sql.ResultSet, java.sql.Statement"%>

<%
	String pageName = "form.jsp";
	String pageFolder = "area_studente";
	UserInterface u =(UserInterface) request.getSession().getAttribute("user");
	Request r = RequestDAO.existPendent(u.getEmail(), 1);
	if(r!=null){
		request.setAttribute("request", r);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/area_studente/uploadAttached.jsp");
		rd.forward(request, response);
	}
	
%>

<!-- CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	} -->

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/base_pieces/head.jsp" />
</head>

<body onLoad="">
	<div class="page-wrapper">


		<jsp:include page="/base_pieces/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage firstFormPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 firstForm-container">
									<div class="panel">
										<h2 class="text-center">Richiesta</h2>
										<p class="text-center">Compila tutti i campi per
											effettuare la richiesta</p>
									</div>
									<form id="firstForm" action="${pageContext.request.contextPath}/ServletRegistrazioneFormStudent" method="post">

										<div class="form-group">
											<label for="year">Anno di
												immatricolazione:</label> <select class="form-control"
												id="year" name="year"required>
												<%
											    	Integer range = Integer.parseInt(new SystemAttribute().getValueByKey("request-matriculation-year-range"));
											    	for(int i = (range*-1); i <= 0; i++){
											    	  LocalDate year = LocalDate.now().plusYears(i);
											    	  LocalDate nextYear = LocalDate.now().plusYears(i+1);
											    	  %>
												<option value="<%= year.getYear() %>"><%= year.getYear() %>/<%= nextYear.getYear() %></option>
												<%
											    	}
											    %>
											</select>
										</div>

										<div class="form-group">
											<!-- Matricola -->
											<label for="matricola">Matricola</label> <input
												class="form-control" type="number" value="0123456789"
												id="matricola" name="matricola"required>
										</div>
										
										<div class="form-group">
											<!-- Ore di tirocinio effettuate -->
											<label for="hours">Ore di tirocinio effettuate</label><br>
											 <input class="form-control" type="number" id="hours" name="hours" required>
										</div>

										<div class="form-group">
											<label for="azienda">Azienda presso cui si è svolto il tirocinio:</label> <select
												class="form-control" id="azienda" name="azienda" required>
												<%try{
												    ArrayList<Azienda> list = AziendaDAO.doRetrieveByState(1);
												        for(Azienda x:list){
												    	  %>
												<option value="<%= x.getId() %>"><%= x.getName() %></option>
												<%												          
												        }
												      } catch (Exception e) {
												        System.out.println(e.getMessage());
												      }  											    													    
											    %>
											</select>
										</div>

										<div class="form-group">
											<label for="startDate" class="col-2 col-form-label">Data
												di inizio tirocinio</label> <input class="form-control"
												type="date" value="2011-08-19" name="startDate" id="startDate">
										</div>

										<div class="form-group">
											<label for="endDate" class="col-2 col-form-label">Data
												di fine tirocinio</label> <input class="form-control"
												type="date" value="2011-08-19" name="endDate" id="endDate">
										</div>

										<div class="form-group">
											<label for="cfu">CFU da Conseguire:</label> <select
												class="form-control" id="cfu" name="cfu" required>
												<option>6</option>
												<option>12</option>
											</select>
										</div>

										<div class="form-group">
											<button id="submit" type="submit" class="btn btn-primary btn-submit">Invia</button>
										</div>

										<div class="clearfix"></div>
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/base_pieces/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/base_pieces/includes.jsp" />

</body>
</html>
