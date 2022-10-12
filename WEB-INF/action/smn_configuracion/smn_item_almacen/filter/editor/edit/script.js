setComboValue('smn_item_rf','${fld:smn_item_rf}','form1');
setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('ria_estatus','${fld:ria_estatus}','form1');
document.form1.ria_vigencia.value='${fld:ria_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_item_almacen_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

