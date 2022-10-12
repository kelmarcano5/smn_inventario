document.getElementById("smn_movimiento_cabecera_id").disabled=true;
document.getElementById("mde_cantidad_solicitada").disabled=true;
document.getElementById("smn_item_rf").disabled=true;
document.getElementById("calendario_lote").hidden=true;
document.form1.fecha_vencimiento_lote.disabled=true;
document.form1.smn_movimiento_cabecera_id.value='${fld:smn_movimiento_cabecera_id}';
setComboValue('smn_item_rf','${fld:smn_item_rf}','form1');

obtenerlote('${fld:smn_item_rf}','smn_item_rf_name');

setComboValue('smn_centro_costo_rf','${fld:smn_centro_costo_rf}','form1');

if('${fld:mde_lote@#,###,###}' != 0)
	document.form1.mde_lote.value='${fld:mde_lote}';
else
	document.form1.mde_lote.value='';

document.form1.mde_cantidad_solicitada.value='${fld:mde_cantidad_solicitada}';
document.form1.fecha_vencimiento_lote.value='${fld:mde_fecha_vencimiento_lote@dd-MM-yyyy}';
document.form1.mde_cantidad_recibida.value='${fld:mde_cantidad_por_recibir}';
document.form1.mde_cantidad_recibida1.value='${fld:mde_cantidad_recibida}';
document.form1.mde_descripcion.value='${fld:mde_descripcion@js}';
setComboValue('mde_es_bonificacion','${fld:mde_es_bonificacion}','form1');
document.form1.mde_valor_unitario_ml.value='${fld:mde_valor_unitario_ml}';
document.form1.mde_valor_unitario_ma.value='${fld:mde_valor_unitario_ma}';
document.form1.mde_monto_bruto_ml.value='${fld:mde_monto_bruto_ml}';
document.form1.mde_monto_bruto_ma.value='${fld:mde_monto_bruto_ma}';
document.form1.mde_monto_disminucion_ml.value='${fld:mde_monto_disminucion_ml}';
document.form1.mde_monto_disminucion_ma.value='${fld:mde_monto_disminucion_ma}';
document.form1.mde_monto_valor_agregado_ml.value='${fld:mde_monto_valor_agregado_ml}';
document.form1.mde_monto_valor_agregado_ma.value='${fld:mde_monto_valor_agregado_ma}';
document.form1.mde_monto_neto_ml.value='${fld:mde_monto_neto_ml}';
document.form1.mde_monto_neto_ma.value='${fld:mde_monto_neto_ma}';
document.form1.mde_estatus.value = '${fld:mde_estatus}';
document.form1.id.value='${fld:smn_movimiento_detalle_id}';

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");
