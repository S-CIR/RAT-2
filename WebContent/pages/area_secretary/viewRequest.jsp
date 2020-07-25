<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="src.controller.CheckSession"%>
<%@ page import="java.util.*,src.model.Request"%>

<%
	String pageName = "viewRequest.jsp";
	String pageFolder = "area_secretary";
	
	int num = (int) request.getAttribute("req_num");
	int req_ids[] = new int[10];
	String matricole[] = new String[10];
	String nomi[] = new String[10];
	String cognomi[] = new String[10];
	int hours[] = new int[10];
	int req_cfu[] = new int[10];
	int val_cfu[] = new int[10];
	String state_desc[] = new String[10];
	String req_filenames[] = new String[10];
	if(num > 0){
		req_ids =(int[]) request.getAttribute("req_ids");
		matricole = (String[]) request.getAttribute("matricole");
		nomi = (String[]) request.getAttribute("nomi");
		cognomi = (String[]) request.getAttribute("cognomi");
		hours = (int[]) request.getAttribute("hours");
		req_cfu = (int[]) request.getAttribute("req_cfu");
		val_cfu = (int[]) request.getAttribute("val_cfu");
		state_desc = (String[]) request.getAttribute("state_desc");
		req_filenames = (String[]) request.getAttribute("req_filenames");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Segreteria: Richieste</title>
<jsp:include page="/base_pieces/head.jsp" />
</head>
<body onLoad="showData()">
	<div class="page-wrapper">


		<jsp:include page="/base_pieces/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage viewRequestSecretary">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<table id="bodySegretaryTable" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="text-center">ID</th>
											<th class="text-center">Allegati</th>
											<th class="text-center">Matricola</th>
											<th class="text-center">Nome</th>
											<th class="text-center">Cognome</th>
											<th class="text-center">Ore</th>
											<th class="text-center">CFU Richiesti</th>
											<th class="text-center">CFU Conv.</th>
											<th class="text-center">Stato</th>
											<th class="text-center">Azioni</th>
										</tr>
									</thead>
									<tbody id="bodySegretaryBody">
									<%if(num>0){
											for(int i=0;i<num;i++){%>
												<tr><td><%=req_ids[i]%></td><td><%=req_filenames[i]%></td><td><%=matricole[i]%></td><td><%=nomi[i]%></td>
												<td><%=cognomi[i]%></td><td><%=hours[i]%></td>
												<td><%=req_cfu[i]%></td><td><%=val_cfu[i]%></td><td><%=state_desc[i] %></td>
												<%if (state_desc[i].equals("In elaborazione dalla Segreteria")){ %>
													<td><a href="<%=request.getContextPath()%>/ServletInoltro?req_id=<%=req_ids[i]%>&next_state=3&user_id=1" class="btn btn-primary btn-submit">Inoltra</a>
													<%} else if (state_desc[i].equals("Accettata e In elaborazione dal Consiglio Didattico")) {%>
													<td><a href="<%=request.getContextPath()%>/ServletConvalidaCFU?req_id=<%=req_ids[i]%>" class="btn btn-primary btn-submit">Convalida</a>
													<%} %>
													<a href="<%=request.getContextPath()%>/ServletInoltro?req_id=<%=req_ids[i]%>&next_state=7&user_id=1" class="btn btn-primary btn-submit">Rifiuta</a></td>
												</tr>
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

	<!--  <script>
			jQuery(document).ready(function($){
				$('#bodySegretaryTable').DataTable( {
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
	</script> -->
</body>
</html>