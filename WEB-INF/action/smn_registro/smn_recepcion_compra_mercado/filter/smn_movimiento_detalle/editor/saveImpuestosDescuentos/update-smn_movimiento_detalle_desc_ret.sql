UPDATE smn_inventario.smn_movimiento_detalle_desc_ret SET
	mdd_monto_base_ml = ${fld:mde_monto_bruto_ml},
	smn_porcentaje_rf = ${fld:dyr_porcentaje_descuento},
	mdd_monto_descuento_ml = ${fld:total_descuento_ml_redondeo},
	smn_moneda_rf = ${fld:smn_moneda_rf_detalle},
	smn_tasa_rf = ${fld:smn_tasa_rf_detalle},
	mdd_monto_base_ma = ${fld:mde_monto_bruto_ma},
	mdd_monto_descuento_ma = ${fld:total_descuento_ma_redondeo},
	mdd_idioma = '${def:locale}',
	mdd_usuario = '${def:user}',
	mdd_fecha_registro = {d '${def:date}'},
	mdd_hora = '${def:time}'
WHERE
	smn_descuento_retencion_id = ${fld:smn_descuento_retencion_id}