setComboValue('smn_entidad_rf','${fld:smn_entidad_rf}','form1');
setComboValue('smn_sucursal_rf','${fld:smn_sucursal_rf}','form1');
setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_modulo_rf','${fld:smn_modulo_rf}','form1');
setComboValue('smn_documento_origen_rf','${fld:smn_documento_origen_rf}','form1');
document.form1.mca_numero_documento_origen.value='${fld:mca_numero_documento_origen@#,###,###}';
setComboValue('smn_documento_id','${fld:smn_documento_id}','form1');
document.form1.mca_numero.value='${fld:mca_numero@#,###,###}';
setComboValue('smn_tipo_documento_pago_id','${fld:smn_tipo_documento_pago_id}','form1');
setComboValue('smn_proveedor_rf','${fld:smn_proveedor_rf}','form1');
setComboValue('smn_orden_compra_rf','${fld:smn_orden_compra_rf}','form1');
document.form1.mca_recibo.value='${fld:mca_recibo@#,###,###}';
document.form1.mca_monto_documento_ml.value='${fld:mca_monto_documento_ml@#,###,##0.00}';
document.form1.mca_monto_documento_ma.value='${fld:mca_monto_documento_ma@#,###,##0.00}';
document.form1.mca_monto_diminucion_ml.value='${fld:mca_monto_diminucion_ml@#,###,##0.00}';
document.form1.mca_monto_diminucion_ma.value='${fld:mca_monto_diminucion_ma@#,###,##0.00}';
document.form1.mca_monto_valor_agregado_ml.value='${fld:mca_monto_valor_agregado_ml@#,###,##0.00}';
document.form1.mca_monto_valor_agregado_ma.value='${fld:mca_monto_valor_agregado_ma@#,###,##0.00}';
document.form1.mca_monto_neto_ml.value='${fld:mca_monto_neto_ml@#,###,##0.00}';
document.form1.mcc_monto_neto_ma.value='${fld:mcc_monto_neto_ma@#,###,##0.00}';
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
document.form1.mca_fecha_recibida.value='${fld:mca_fecha_recibida@dd-MM-yyyy}';
setComboValue('mca_estatus_proceso','${fld:mca_estatus_proceso}','form1');
setComboValue('mca_estatus_financiero','${fld:mca_estatus_financiero}','form1');
document.form1.id.value='${fld:smn_movimiento_cabecera_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

