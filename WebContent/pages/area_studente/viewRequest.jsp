<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="src.controller.CheckSession"%> 
<%@ page import="java.util.*,src.model.Request"%>
<%
	String pageName = "viewRequest.jsp";
	String pageFolder = "area_studente";
	int num =(int) request.getAttribute("req_num");
	int req_ids[] = new int[2];
	String req_states[] = new String[2];
	String req_filenames[] = new String[2];
	if(num > 0){
		req_ids =(int[]) request.getAttribute("req_ids");
		req_states = (String[])request.getAttribute("req_states");
		req_filenames = (String[]) request.getAttribute("req_filenames");
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

<body onLoad="showData()">
	<div class="page-wrapper">

		<jsp:include page="/base_pieces/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage viewRequestStudent">
			<form class="form-group" action="pages/area_studente/form.jsp">
			<button type="submit" style="margin:auto; display:block; width:20%;font-size:20px;" class="btn btn-primary btn-submit" <%if(num==2){%>disabled<%}%>>Inserisci una nuova richiesta</button>
		</form>
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<table id="studentTable" style="margin-top: 5%;font-size:18px"
									class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">ID</th>
											<th class="text-center" align="center">Allegati</th>
											<th class="text-center" align="center">Stato</th>
										</tr>
									</thead>
									<tbody>
										<%if(num>0){
											for(int i=0;i<num;i++){%>
												<tr><td><%=req_ids[i]%></td><td><%=req_filenames[i]%></td><td><%=req_states[i]%></td></tr>
										<%	}
										  }else{%>
										  	<tr><td style="text-align:center;"colspan="3">Nessuna richiesta presente.</td></tr>
										  <%}%>
									</tbody>
								</table>

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
