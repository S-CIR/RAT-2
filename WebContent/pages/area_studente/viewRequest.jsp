<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="src.controller.CheckSession"%> 
<%@ page import="java.util.*,src.model.Request"%>
<%
	String pageName = "viewRequest.jsp";
	String pageFolder = "area_studente";
	
	
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
			<button type="submit" style="margin:auto; display:block;" class="btn btn-primary btn-submit">Inserisci una nuova richiesta</button>
		</form>
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<table id="studentTable" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">ID</th>
											<th class="text-center" align="center">Allegati</th>
											<th class="text-center" align="center">Stato</th>
										</tr>
									</thead>
									<tbody id="bodyStudentTable">
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
 
	<script>
			jQuery(document).ready(function($){
				$('#studentTable').DataTable( {
			        "order": [[ 0, "desc" ]],
			        "lengthMenu": [[10, -1], [10, "Tutti"]],
			        "autoWidth": false,
			        "bAutoWidth": false,			        
			        "language": {
						    "sEmptyTable":     "Nessuna Richiesta Presente",
						    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						    "sInfoPostFix":    "",
						    "sInfoThousands":  ".",
						    "sLengthMenu":     "Visualizza _MENU_ elementi",
						    "sLoadingRecords": "Caricamento...",
						    "sProcessing":     "Elaborazione...",
						    "sSearch":         "Cerca:",
						    "sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						    "oPaginate": {
						        "sFirst":      "Inizio",
						        "sPrevious":   '<i class="fa fa-caret-left"></i>',
						        "sNext":       '<i class="fa fa-caret-right"></i>',
						        "sLast":       "Fine"
						    },
						    "oAria": {
						        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
						        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
						    }
			        }        
			    } );
			});
		</script>
	<script
		src="<%= request.getContextPath() %>/js/js_viewRequestsStudent.js"></script>

		
</body>
</html>
