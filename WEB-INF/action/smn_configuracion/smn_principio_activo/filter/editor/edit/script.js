document.form1.pac_codigo.value='${fld:pac_codigo@#,###,###}';
document.form1.pac_descripcion.value='${fld:pac_descripcion@js}';
document.form1.pac_descripcion_completa.value='${fld:pac_descripcion_completa@js}';
setComboValue('pac_estatus','${fld:pac_estatus}','form1');
document.form1.pac_fecha_vigencia.value='${fld:pac_fecha_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_principio_activo_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.pac_codigo.disabled=true;
 

