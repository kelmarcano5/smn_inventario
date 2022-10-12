document.form1.pre_codigo.value='${fld:pre_codigo@js}';
document.form1.pre_descripcion.value='${fld:pre_descripcion@js}';
setComboValue('pre_estatus','${fld:pre_estatus}','form1');
document.form1.id.value='${fld:smn_presentacion_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.pre_codigo.disabled=true;
 

