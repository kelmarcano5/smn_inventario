UPDATE smn_inventario.smn_movimiento_detalle_desc_ret SET
	mdd_monto_base_ml = ${fld:mde_monto_bruto_ml},
	mdd_monto_base_ma = ${fld:mde_monto_bruto_ma},
	mdd_monto_descuento_ml = ${fld:mdd_monto_descuento_ml},
	mdd_monto_descuento_ma = ${fld:mdd_monto_descuento_ma},
	mdd_idioma = '${def:locale}',
  	mdd_usuario = '${def:user}',
  	mdd_fecha_registro = {d '${def:date}'},
  	mdd_hora = '${def:time}'
WHERE
	smn_descuento_retencion_id = ${fld:smn_descuento_retencion_id}