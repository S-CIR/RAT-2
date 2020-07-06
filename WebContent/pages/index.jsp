<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="src.controller.CheckSession"%> 

<%
	String pageName = "index.jsp";
	String pageFolder = "pages";
%>

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


		<div class="sidebar-page-container basePage indexPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
 
								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 index-container">
									<div class="panel">
										<h2 class="text-center">Benvenuto/a</h2>
										<p></p>
									</div>
									<a href=".\login.jsp" class="btn btn-primary btn-lg btn-block"
										role="button" aria-pressed="true">Accedi</a>
									<p></p>
									<a href="area_studente\signUp.jsp"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Registrati</a>
									<p></p>
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
	<script src="<%= request.getContextPath() %>/js/pages/scripts_login.js"></script>

</body>
</html>