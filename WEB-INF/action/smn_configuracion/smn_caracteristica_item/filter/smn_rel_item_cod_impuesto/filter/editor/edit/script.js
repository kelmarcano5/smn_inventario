setComboValue('smn_caracteristica_item_id','${fld:smn_caracteristica_item_id}','form1');
setComboValue('smn_codigo_impuesto_rf','${fld:smn_codigo_impuesto_rf}','form1');
document.form1.id.value='${fld:smn_rel_item_cod_impuesto_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

