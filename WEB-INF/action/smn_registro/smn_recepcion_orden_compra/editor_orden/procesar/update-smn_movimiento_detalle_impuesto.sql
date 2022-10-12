UPDATE smn_inventario.smn_movimiento_detalle_impuesto SET
	mdi_monto_base = ${fld:mde_monto_bruto_ml},
	mdi_monto_impuesto_ml = ${fld:mdi_monto_impuesto_ml},
	mdi_monto_impuesto_ma = ${fld:mdi_monto_impuesto_ma},
	mdi_idioma = '${def:locale}',
  	mdi_usuario = '${def:user}',
  	mdi_fecha_registro = {d '${def:date}'},
  	mdi_hora = '${def:time}'
WHERE
	smn_mov_det_impuesto_id  = ${fld:smn_mov_det_impuesto_id}