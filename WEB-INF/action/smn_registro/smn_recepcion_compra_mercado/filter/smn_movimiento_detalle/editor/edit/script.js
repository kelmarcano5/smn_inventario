document.form1.smn_movimiento_cabecera_id.value='${fld:smn_movimiento_cabecera_id}';
document.form1.id.value='${fld:smn_movimiento_detalle_id}';
$('#smn_item_rf').val('${fld:smn_item_rf}').trigger('change');
document.form1.smn_centro_costo_rf.value='${fld:smn_centro_costo_id}';
document.form1.smn_centro_costo_name.value='${fld:smn_centro_costo_combo}';
document.form1.mde_lote.value='${fld:mde_lote}';
document.form1.mde_fecha_vencimiento_lote.value='${fld:mde_fecha_vencimiento_lote@dd-MM-yyyy}';
setComboValue('mde_tipo_movimiento','${fld:mde_tipo_movimiento}','form1');
document.form1.mde_cantidad_recibida.value='${fld:mde_cantidad_recibida@#,###,###}';
document.form1.mde_descripcion.value='${fld:mde_descripcion@js}';
$('#mde_es_bonificacion').val('${fld:mde_es_bonificacion}').trigger('change');
$('#smn_unidad_medida_rf').val('${fld:smn_unidad_medida_rf}').trigger('change');
document.form1.mde_valor_unitario_ml.value=formatear_monto('${fld:mde_valor_unitario_ml@#,###,###}');
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
document.form1.mde_valor_unitario_ma.value=formatear_monto('${fld:mde_valor_unitario_ma@#,###,###}');
document.form1.mde_monto_bruto_ml.value=formatear_monto('${fld:mde_monto_bruto_ml@#,###,###}');
document.form1.mde_monto_bruto_ma.value=formatear_monto('${fld:mde_monto_bruto_ma@#,###,###}');
setComboValue('mde_estatus','${fld:mde_estatus}','form1');


<descuentos_rows>
	selectDescuentos('${fld:id}','${fld:item}','${fld:dyr_porcentaje_base}','${fld:smn_porcentaje_rf}','${fld:dyr_apli_cant_precio}','${fld:mdd_monto_descuento_ml}','AG');
</descuentos_rows>

<impuestos_rows>
	selectImpuestos('${fld:id}','${fld:item}','${fld:imp_tipo_codigo}','${fld:imp_porcentaje_base}','${fld:smn_porcentaje_impuesto_rf}','${fld:imp_monto_sustraendo}','AG');
</impuestos_rows>

if(document.form1.mde_valor_unitario_ml.value != "")
{
	$(".impuestos").css("pointer-events","");
	$(".descuentos").css("pointer-events","");
}
else
{
	$(".impuestos").css("pointer-events","none");
	$(".descuentos").css("pointer-events","none");
}

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

