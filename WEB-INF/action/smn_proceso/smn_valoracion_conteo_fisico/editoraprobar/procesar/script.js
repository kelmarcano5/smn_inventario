obteneridcab();

function obteneridcab() {
		alert("ENTRO EN EL OBTENER ID CABECERA MOVIMIENTO");
	ajaxCall(httpMethod="GET", uri="${def:actionroot}/getobteneridcab", 
			divResponse=null, 
			divProgress="status", 
			formName="form1", 
			afterResponseFn=generar('smn_movimiento_cabecera_id'), //function 'setElementFirstIndex' select first element of combo list
			onErrorFn=null); 				
}

function generar(id){
	uri = "${def:context}${def:actionroot}/procesar_control_item?smn_movimiento_cabecera_id="+ id; 
	alert(uri);
	openDialog("dialog", uri, 350, 100);
}
alertBox ('Su conteo fisico ha sido aprobado', '${lbl:b_continue_button}', null, null);