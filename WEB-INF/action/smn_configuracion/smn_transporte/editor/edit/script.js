document.form1.tra_codigo.value='${fld:tra_codigo@js}';
document.form1.tra_descripcion_transporte.value='${fld:tra_descripcion_transporte@js}';
setComboValue('tra_tipo_transporte','${fld:tra_tipo_transporte}','form1');
setComboValue('smn_activo_rf','${fld:smn_activo_rf}','form1');
setComboValue('smn_proveedor_rf','${fld:smn_proveedor_rf}','form1');
setComboValue('tra_estatus','${fld:tra_estatus}','form1');
document.form1.tra_vigencia.value='${fld:tra_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_transporte_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.tra_codigo.disabled=true;
 

