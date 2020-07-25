<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" %> <!-- import="controller.CheckSession" -->

<%
	String pageName = "signUp.jsp";
	String pageFolder = "area_studente";
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/base_pieces/head.jsp" />
</head>

<body onLoad="">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->


		<jsp:include page="/base_pieces/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage signUpPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
									<div class="panel">
										<h2 class="text-center">Registrazione</h2>
										<p class="text-center">Compila tutti i campi per
											registrarti</p>
									</div>
									<form id="signUp" action="${pageContext.request.contextPath}/ServletRegistrazioneStudente" method="post">
<<<<<<< HEAD
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="nome" type="text" class="form-control" name="nome"
												placeholder="Nome" minlength="1" maxlength="20" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="cognome" type="text" class="form-control" name="cognome"
												placeholder="Cognome" minlength="1" maxlength="20" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
=======
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="nome" type="text" class="form-control" name="nome"
												placeholder="Nome" minlength="1" maxlength="20" required>
										</div>
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="cognome" type="text" class="form-control" name="cognome"
												placeholder="Cognome" minlength="1" maxlength="20" required>
										</div>
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
>>>>>>> master
											<input id="email" type="email" class="form-control" name="email"
												placeholder="Email" minlength="1" required>
										</div>

<<<<<<< HEAD
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
=======
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
>>>>>>> master
											<label id="sex" class="radio-inline"><input type="radio"
												class="sex" name="sex" value="M" required>M</label> <label
												class="radio-inline"><input type="radio" class="sex"
												name="sex" value="F" required>F</label>
										</div>

<<<<<<< HEAD
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="password" type="password" class="form-control" name="password"
												placeholder="Password" minlength="8" required>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="verifica"  type="password" class="form-control"
=======
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="password" type="password" class="form-control" name="password"
												placeholder="Password" minlength="8" required>
										</div>
										<div  class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input id="verifica" type="password" class="form-control"
>>>>>>> master
												name="verifyPassword" placeholder="Verifica Password"
												minlength="8" required>
										</div>
										<div 
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button id="submit" type="submit" class="btn btn-primary btn-submit">Registrazione</button>
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
