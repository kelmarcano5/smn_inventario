setComboValue('smn_movimiento_detalle_id','${fld:smn_movimiento_detalle_id}','form1');
setComboValue('smn_cod_impuesto_deduc_rf','${fld:smn_cod_impuesto_deduc_rf}','form1');
document.form1.mdi_monto_base.value='${fld:mdi_monto_base@#,###,##0.00}';
setComboValue('smn_porcentaje_impuesto_rf','${fld:smn_porcentaje_impuesto_rf}','form1');
setComboValue('mdi_sustraendo_rf','${fld:mdi_sustraendo_rf}','form1');
setComboValue('mdi_tipo_movimiento','${fld:mdi_tipo_movimiento}','form1');
document.form1.mdi_monto_impuesto_ml.value='${fld:mdi_monto_impuesto_ml@#,###,##0.00}';
setComboValue('smn_moneda','${fld:smn_moneda}','form1');
setComboValue('smn_tasa','${fld:smn_tasa}','form1');
document.form1.mdi_monto_impuesto_ma.value='${fld:mdi_monto_impuesto_ma@#,###,##0.00}';
document.form1.id.value='${fld:smn_mov_det_impuesto_id@#######}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

