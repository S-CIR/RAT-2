<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String pageName = "logout.jsp";
	String pageFolder = "";
	request.getSession().invalidate();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/base_pieces/head.jsp" />
<title>RAT - Logout</title>
</head>
<body onLoad="">
	<div class="page-wrapper">
	
		<jsp:include page="/base_pieces/includes.jsp" />

		<jsp:include page="/base_pieces/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage logoutPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 logout-container">
									<h2 class="text-center">Logout effettuato con successo.</h2>
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

</body>
</html>