UPDATE smn_inventario.smn_movimiento_detalle_desc_ret SET
	smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id},
	smn_codigo_descuento_rf=${fld:smn_codigo_descuento_rf},
	mdd_monto_base_ml=${fld:mdd_monto_base_ml},
	smn_porcentaje_rf=${fld:smn_porcentaje_rf},
	mdd_monto_descuento_ml=${fld:mdd_monto_descuento_ml},
	smn_moneda_rf=${fld:smn_moneda_rf},
	smn_tasa_rf=${fld:smn_tasa_rf},
	mdd_monto_base_ma=${fld:mdd_monto_base_ma},
	mdd_monto_descuento_ma=${fld:mdd_monto_descuento_ma},
	mdd_idioma='${def:locale}',
	mdd_usuario='${def:user}',
	mdd_fecha_registro='${def:date}',
	mdd_hora='${def:time}'

WHERE
	smn_movimiento_detalle_desc_ret_id=${fld:id}

