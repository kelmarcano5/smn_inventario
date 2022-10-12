UPDATE smn_inventario.smn_movimiento_detalle SET
	smn_movimiento_cabecera_id=${fld:smn_movimiento_cabecera_id},
	smn_item_rf=${fld:smn_item_rf},
	smn_activo_fijo_rf=${fld:smn_activo_fijo_rf},
	smn_centro_costo_rf=${fld:smn_centro_costo_rf},
	mde_es_bonificacion=${fld:mde_es_bonificacion},
	mde_cantidad=${fld:mde_cantidad},
	mde_lote=${fld:mde_lote},
	mde_fecha_elaboracion_lote=${fld:mde_fecha_elaboracion_lote},
	mde_fecha_vencimiento_lote=${fld:mde_fecha_vencimiento_lote},
	mde_descripcion=${fld:mde_descripcion},
	smn_unidad_medida_rf=${fld:smn_unidad_medida_rf},
	smn_precio_ml=${fld:smn_precio_ml},
	smn_tasa_rf=${fld:smn_tasa_rf},
	smn_moneda_rf=${fld:smn_moneda_rf},
	smn_precio_ma=${fld:smn_precio_ma},
	mde_monto_documento_ml=${fld:mde_monto_documento_ml},
	mde_monto_documento_ma=${fld:mde_monto_documento_ma},
	mde_monto_disminucion_ml=${fld:mde_monto_disminucion_ml},
	mde_monto_disminucion_ma=${fld:mde_monto_disminucion_ma},
	mde_monto_valor_agregado_ml=${fld:mde_monto_valor_agregado_ml},
	mde_monto_valor_agregado_ma=${fld:mde_monto_valor_agregado_ma},
	mde_monto_neto_ml=${fld:mde_monto_neto_ml},
	mde_monto_neto_ma=${fld:mde_monto_neto_ma},
	mde_estatus=${fld:mde_estatus},
	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'

WHERE
	smn_movimiento_detalle_id=${fld:id}

