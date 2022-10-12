setComboValue('smn_caracteristica_item_id','${fld:smn_caracteristica_item_id}','form1');
setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
document.form1.id.value='${fld:smn_rel_item_almacen_id@#,###,###}';

document.form1.ria_cantidad_max.value='${fld:ria_cantidad_max@#,###,###}';
document.form1.ria_cantidad_min.value='${fld:ria_cantidad_min@#,###,###}';
document.form1.ria_punto_reorden.value='${fld:ria_punto_reorden@#,###,###}';
document.form1.ria_cantidad_seguridad.value='${fld:ria_cantidad_seguridad@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

