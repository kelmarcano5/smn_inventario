document.form1.smn_movimiento_cabecera_id_name.value='${fld:mca_estatus_proceso_pl0@js}';
document.form1.smn_movimiento_cabecera_id.value='${fld:smn_movimiento_cabecera_id@#,###,###}';
setComboValue('smn_item_rf','${fld:smn_item_rf}','form1');
setComboValue('smn_activo_fijo_rf','${fld:smn_activo_fijo_rf}','form1');
setComboValue('smn_centro_costo_rf','${fld:smn_centro_costo_rf}','form1');
document.form1.mde_lote.value='${fld:mde_lote@#,###,###}';
document.form1.mde_fecha_vencimiento_lote.value='${fld:mde_fecha_vencimiento_lote@dd-MM-yyyy}';
setComboValue('mde_tipo_movimiento','${fld:mde_tipo_movimiento}','form1');
document.form1.mde_cantidad_recibida.value='${fld:mde_cantidad_recibida@#,###,###}';
document.form1.mde_descripcion.value='${fld:mde_descripcion@js}';
setComboValue('mde_es_bonificacion','${fld:mde_es_bonificacion}','form1');
setComboValue('smn_unidad_medida_rf','${fld:smn_unidad_medida_rf}','form1');
document.form1.mde_valor_unitario_ml.value='${fld:mde_valor_unitario_ml@#,###,###}';
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
document.form1.mde_valor_unitario_ma.value='${fld:mde_valor_unitario_ma@#,###,###}';
document.form1.mde_monto_bruto_ml.value='${fld:mde_monto_bruto_ml@#,###,##0.00}';
document.form1.mde_monto_bruto_ma.value='${fld:mde_monto_bruto_ma@#,###,##0.00}';
document.form1.mde_monto_disminucion_ml.value='${fld:mde_monto_disminucion_ml@#,###,##0.00}';
document.form1.mde_monto_disminucion_ma.value='${fld:mde_monto_disminucion_ma@#,###,##0.00}';
document.form1.mde_monto_valor_agregado_ml.value='${fld:mde_monto_valor_agregado_ml@#,###,##0.00}';
document.form1.mde_monto_valor_agregado_ma.value='${fld:mde_monto_valor_agregado_ma@#,###,##0.00}';
document.form1.mde_monto_neto_ml.value='${fld:mde_monto_neto_ml@#,###,##0.00}';
document.form1.mde_monto_neto_ma.value='${fld:mde_monto_neto_ma@#,###,##0.00}';
setComboValue('mde_estatus','${fld:mde_estatus}','form1');
document.form1.id.value='${fld:smn_movimiento_detalle_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

