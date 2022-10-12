setComboValue('smn_caracteristica_item_id','${fld:smn_caracteristica_item_id}','form1');
document.form1.smn_item_id.value='${fld:smn_item_id@#,###,###}';
document.form1.icd_cantidad.value='${fld:icd_cantidad@#,###,##0.00}';
setComboValue('smn_unidad_medida_rf','${fld:smn_unidad_medida_rf}','form1');
setComboValue('icd_estatus','${fld:icd_estatus}','form1');
document.form1.icd_vigencia.value='${fld:icd_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_item_compuesto_detalle_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

