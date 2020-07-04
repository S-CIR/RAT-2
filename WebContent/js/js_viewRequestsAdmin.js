$(document).ready(function() {

});

function showData() {

	$(".preloader").show();

	$.ajax({
		url : absolutePath + "/ServletRichiesteAdmin",
		type : "POST",
		dataType : 'JSON',
		async : false,
		success : function(msg) {
			if (!msg.result) {
				showAlert(1, msg.error);
			} else {
				$("#bodyAdminTable").html(msg.content);
			}
		},
		error : function(msg) {
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});

	$(".preloader").hide();
}