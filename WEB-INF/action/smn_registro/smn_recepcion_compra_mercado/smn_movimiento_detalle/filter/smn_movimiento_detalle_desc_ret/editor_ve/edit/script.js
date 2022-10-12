setComboValue('smn_movimiento_detalle_id','${fld:smn_movimiento_detalle_id}','form1');
setComboValue('smn_codigo_descuento_rf','${fld:smn_codigo_descuento_rf}','form1');
document.form1.mdd_monto_base_ml.value=formatear_monto('${fld:mdd_monto_base_ml@#,###,##0.00}');
document.form1.smn_porcentaje_rf.value=formatear_monto('${fld:smn_porcentaje_rf@#,###,##0.00}');

document.form1.mdd_monto_descuento_ml.value=formatear_monto('${fld:mdd_monto_descuento_ml@#,###,##0.00}');
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
cambiarmoneda(); 
var codigo_descuento = document.getElementById('smn_codigo_descuento_rf').value;
if(codigo_descuento != '' && codigo_descuento != null)
document.form1.smn_moneda_rf.disabled=false;
getTasa('${fld:smn_moneda_rf}');
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
document.form1.mdd_monto_base_ma.value=formatear_monto('${fld:mdd_monto_base_ma@#,###,##0.00}');
document.form1.mdd_monto_descuento_ma.value=formatear_monto('${fld:mdd_monto_descuento_ma@#,###,##0.00}');
document.form1.id.value='${fld:smn_descuento_retencion_id@#######}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

