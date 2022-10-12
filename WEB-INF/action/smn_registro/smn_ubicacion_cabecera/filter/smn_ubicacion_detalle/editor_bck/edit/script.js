document.form1.smn_ubicacion_cabecera_id.value='${fld:smn_ubicacion_cabecera_id@#,###,###}';
setComboValue('smn_divisiones_id','${fld:smn_divisiones_id}','form1');
setComboValue('smn_subdivisiones_id','${fld:smn_subdivisiones_id}','form1');
setComboValue('smn_lote_id','${fld:smn_lote_id}','form1');
document.form1.ubd_cantidad_inicial.value='${fld:ubd_cantidad_inicial@#,###,##0.00}';
document.form1.ubd_entrada.value='${fld:ubd_entrada@#,###,##0.00}';
document.form1.ubd_salida.value='${fld:ubd_salida@#,###,##0.00}';
document.form1.ubd_cantidad_final.value='${fld:ubd_cantidad_final@#,###,##0.00}';
document.form1.id.value='${fld:smn_ubicacion_detalle_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

