UPDATE smn_inventario.smn_movimiento_detalle_impuesto SET
	smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id},
	smn_cod_impuesto_deduc_rf=${fld:smn_cod_impuesto_deduc_rf},
	mdi_monto_base=${fld:mdi_monto_base},
	smn_porcentaje_impuesto_rf=${fld:smn_porcentaje_impuesto_rf},
	mdi_sustraendo_rf=${fld:mdi_sustraendo_rf},
	mdi_tipo_movimiento=${fld:mdi_tipo_movimiento},
	mdi_monto_impuesto_ml=${fld:mdi_monto_impuesto_ml},
	smn_moneda=${fld:smn_moneda},
	smn_tasa=${fld:smn_tasa},
	mdi_monto_impuesto_ma=${fld:mdi_monto_impuesto_ma},
	mdi_idioma='${def:locale}',
	mdi_usuario='${def:user}',
	mdi_fecha_registro={d '${def:date}'},
	mdi_hora='${def:time}'

WHERE
	smn_mov_det_impuesto_id=${fld:id}

