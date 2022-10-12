setComboValue('smn_caracteristica_item','${fld:smn_caracteristica_item}','form1');
setComboValue('smn_codigo_impuesto_rf','${fld:smn_codigo_impuesto_rf}','form1');
document.form1.id.value='${fld:smn_rel_item_cod_impuesto_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "Editar registro";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");

