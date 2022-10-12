setComboValue('smn_entidad_rf','${fld:smn_entidad_rf}','form1');
setComboValue('smn_sucursal_rf','${fld:smn_sucursal_rf}','form1');
setComboValue('smn_caracteristica_almacen_id','${fld:smn_caracteristica_almacen_id}','form1');
setComboValue('smn_caracteristica_item_id','${fld:smn_caracteristica_item_id}','form1');
document.form1.col_lote.value='${fld:col_lote@js}';
document.form1.col_fecha_vencimiento.value='${fld:col_fecha_vencimiento@dd-MM-yyyy}';
document.form1.col_cantidad_inicial.value='${fld:col_cantidad_inicial@#,###,##0.00}';
document.form1.col_entradas.value='${fld:col_entradas@#,###,##0.00}';
document.form1.col_salidas.value='${fld:col_salidas@#,###,##0.00}';
document.form1.col_cantidad_final.value='${fld:col_cantidad_final@#,###,##0.00}';
document.form1.id.value='${fld:smn_control_lote_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

