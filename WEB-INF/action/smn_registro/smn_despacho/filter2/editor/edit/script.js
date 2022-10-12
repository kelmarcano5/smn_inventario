setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_zona_rf','${fld:smn_zona_rf}','form1');
setComboValue('smn_ruta_id','${fld:smn_ruta_id}','form1');
setComboValue('smn_transporte_id','${fld:smn_transporte_id}','form1');
setComboValue('smn_direccion_rf','${fld:smn_direccion_rf}','form1');
document.form1.id.value='${fld:smn_despacho_id@#,###,###}';

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

