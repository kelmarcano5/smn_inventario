INSERT INTO smn_inventario.smn_movimiento_detalle(
	smn_movimiento_detalle_id,
	smn_movimiento_cabecera_id,
	smn_item_rf,
	smn_centro_costo_rf,
	mde_lote,
	mde_fecha_vencimiento_lote,
	mde_tipo_movimiento,
	mde_cantidad_recibida,
	mde_descripcion,
	mde_es_bonificacion,
	smn_unidad_medida_rf,
	mde_valor_unitario_ml,
	mde_valor_unitario_ma,
	mde_monto_bruto_ml,
	mde_monto_bruto_ma, 
	mde_monto_disminucion_ml,
	mde_monto_disminucion_ma,
	mde_monto_valor_agregado_ml,
	mde_monto_valor_agregado_ma,
	mde_monto_neto_ml,
	mde_monto_neto_ma,
	smn_tasa_rf,
	mde_estatus, 
	mde_idioma,
	mde_usuario,
	mde_fecha_registro,
	mde_hora 
)VALUES(
	nextval('smn_inventario.seq_smn_movimiento_detalle'),
	${fld:smn_movimiento_cabecera_id},
	${fld:smn_item_rf},
	${fld:smn_centro_costo_rf},
	${fld:mde_lote},
	${fld:mde_fecha_vencimiento_lote},
	'SA',
	${fld:dde_cantidad_despachada},
	'****',
	'N',
	${fld:smn_unidad_medida_venta_rf},
	/*mde_monto_disminucion_ml*/0,
	/*mde_monto_disminucion_ma*/0,
	/*mde_monto_valor_agregado_ml*/0,
	/*mde_monto_valor_agregado_ma*/0,
	/*mde_monto_neto_ml*/0,
	/*mde_monto_neto_ma*/0,
	${fld:dde_costo_ml},
	${fld:dde_costo_ma},
	${fld:mde_monto_bruto_ml},
	${fld:mde_monto_bruto_ma},
	${fld:smn_tasa_rf},
	'GE',
	'${def:locale}',
    '${def:user}',
    {d '${def:date}'},
    '${def:time}'
)
RETURNING smn_movimiento_detalle_id as movimiento_detalle_id;