setComboValue('smn_movimiento_detalle_id','${fld:smn_movimiento_detalle_id}','form1');
setComboValue('smn_codigo_descuento_rf','${fld:smn_codigo_descuento_rf}','form1');
document.form1.mdd_monto_base_ml.value='${fld:mdd_monto_base_ml@#,###,##0.00}';
document.form1.smn_porcentaje_rf.value='${fld:smn_porcentaje_rf@#,###,##0.00}';
//setComboValue('smn_porcentaje_rf','${fld:smn_porcentaje_rf}','form1');
document.form1.mdd_monto_descuento_ml.value='${fld:mdd_monto_descuento_ml@#,###,##0.00}';
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
document.form1.mdd_monto_base_ma.value='${fld:mdd_monto_base_ma@#,###,##0.00}';
document.form1.mdd_monto_descuento_ma.value='${fld:mdd_monto_descuento_ma@#,###,##0.00}';
document.form1.id.value='${fld:smn_movimiento_detalle_desc_ret_id@#######}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

