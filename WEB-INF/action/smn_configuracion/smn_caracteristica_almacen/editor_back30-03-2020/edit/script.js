setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('cal_tipo_almacen','${fld:cal_tipo_almacen}','form1');
document.form1.cal_capacidad_almacenaje.value='${fld:cal_capacidad_almacenaje@#,###,##0.00}';
setComboValue('smn_unidad_medida_rf','${fld:smn_unidad_medida_rf}','form1');
document.form1.cal_espacio_fisico.value='${fld:cal_espacio_fisico@#,###,##0.00}';
setComboValue('smn_unidad_medida_espacio_fisico_rf','${fld:smn_unidad_medida_espacio_fisico_rf}','form1');
setComboValue('cal_politica_recepcion','${fld:cal_politica_recepcion}','form1');
setComboValue('cal_estatus','${fld:cal_estatus}','form1');
document.form1.cal_vigencia_desde.value='${fld:cal_vigencia_desde@dd-MM-yyyy}';
document.form1.cal_vigencia_hasta.value='${fld:cal_vigencia_hasta@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_caracteristica_almacen_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

