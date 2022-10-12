document.form1.smn_despacho_id.value='${fld:smn_despacho_id@#,###,###}';
setComboValue('smn_item_rf','${fld:smn_item_rf}','form1');
document.form1.dde_cantidad_solicitada.value='${fld:dde_cantidad_solicitada@#,###,##0.00}';
document.form1.dde_cantidad_despachada.value='${fld:dde_cantidad_despachada@#,###,##0.00}';
document.form1.id.value='${fld:smn_despacho_detalle_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

