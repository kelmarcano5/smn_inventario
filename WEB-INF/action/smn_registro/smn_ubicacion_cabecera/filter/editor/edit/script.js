document.form1.smn_movimiento_detalle_id.value='${fld:smn_movimiento_detalle_id@#,###,###}';
setComboValue('smn_caracteristica_almacen_id','${fld:smn_caracteristica_almacen_id}','form1');
setComboValue('smn_caracteristica_item_id','${fld:smn_caracteristica_item_id}','form1');
setComboValue('ubc_estatus','${fld:ubc_estatus}','form1');
document.form1.id.value='${fld:smn_ubicacion_cabecera_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

