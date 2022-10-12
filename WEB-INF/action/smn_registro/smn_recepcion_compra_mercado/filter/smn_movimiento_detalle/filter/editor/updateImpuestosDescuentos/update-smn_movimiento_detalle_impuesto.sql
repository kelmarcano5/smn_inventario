UPDATE smn_inventario.smn_movimiento_detalle_impuesto SET
	mdi_monto_base=${fld:subtotal_monto_neto_ml},
	smn_porcentaje_impuesto_rf=${fld:imp_porcentaje_calculo},
	mdi_sustraendo_rf=${fld:imp_monto_sustraendo},
	mdi_tipo_movimiento=${fld:imp_tipo_codigo},
	mdi_monto_impuesto_ml=${fld:total_impuesto_ml_redondeo},
	smn_moneda=${fld:smn_moneda_rf_detalle},
	smn_tasa=${fld:smn_tasa_rf_detalle},
	mdi_monto_impuesto_ma=${fld:total_impuesto_ma_redondeo},
	mdi_idioma='${def:locale}',
	mdi_usuario='${def:user}',
	mdi_fecha_registro={d '${def:date}'},
	mdi_hora='${def:time}'
WHERE
	smn_mov_det_impuesto_id = ${fld:smn_mov_det_impuesto_id}