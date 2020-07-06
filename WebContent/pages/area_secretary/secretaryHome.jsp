<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String pageName = "secretaryHome.jsp";
	String pageFolder = "area_secretary";
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/base_pieces/head.jsp"/>
<meta charset="ISO-8859-1">
<title>Area Segreteria: Home</title>
</head>
<body>

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
										<h2 class="text-center">Area Segreteria</h2>
										<p></p>
									</div>
									<a href="/ServletRichiesteSecretary?stateId=1" class="btn btn-primary btn-lg btn-block"
										role="button" aria-pressed="true">Visualizza richieste da inoltrare</a>
									<p></p>
									<a href="/ServletRichiesteSecretary?stateId=4"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Visualizza richieste da convalidare</a>
									<p></p>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

</body>
</html>