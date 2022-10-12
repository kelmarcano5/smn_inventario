document.form1.rut_codigo.value='${fld:rut_codigo@js}';
document.form1.rut_nombre.value='${fld:rut_nombre@js}';
setComboValue('smn_zona_rf','${fld:smn_zona_rf}','form1');
document.form1.rut_posicion_ruta.value='${fld:rut_posicion_ruta@#,###,###}';
setComboValue('rut_estatus','${fld:rut_estatus}','form1');
document.form1.rut_vigencia.value='${fld:rut_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_ruta_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.rut_codigo.disabled=true;
 

