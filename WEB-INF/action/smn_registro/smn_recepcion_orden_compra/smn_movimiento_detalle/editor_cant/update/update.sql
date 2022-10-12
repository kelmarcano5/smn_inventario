UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_lote=${fld:mde_lote},
	mde_fecha_vencimiento_lote=${fld:fecha_vencimiento_lote},
	mde_cantidad_por_recibir=${fld:mde_cantidad_recibida},
	mde_estatus=${fld:mde_estatus},
	mde_valor_unitario_ml=${fld:mde_valor_unitario_ml},
	mde_valor_unitario_ma=${fld:mde_valor_unitario_ma},
  	mde_monto_bruto_ml=${fld:mde_monto_bruto_ml},
  	mde_monto_bruto_ma=${fld:mde_monto_bruto_ma},
  	mde_monto_disminucion_ml=${fld:mde_monto_disminucion_ml},
  	mde_monto_disminucion_ma=${fld:mde_monto_disminucion_ma},
  	mde_monto_valor_agregado_ml=${fld:mde_monto_valor_agregado_ml},
  	mde_monto_valor_agregado_ma=${fld:mde_monto_valor_agregado_ma},
  	mde_monto_neto_ml=${fld:mde_monto_neto_ml},
  	mde_monto_neto_ma=${fld:mde_monto_neto_ma},
	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'
WHERE
	smn_movimiento_detalle_id=${fld:id};