INSERT INTO smn_inventario.smn_movimiento_detalle
(
	smn_movimiento_detalle_id,
	smn_movimiento_cabecera_id,
	smn_item_rf,
	smn_activo_fijo_rf,
	smn_centro_costo_rf,
	mde_lote,
	mde_fecha_vencimiento_lote,
	mde_tipo_movimiento,
	mde_cantidad_recibida,
	mde_descripcion,
	mde_es_bonificacion,
	smn_unidad_medida_rf,
	mde_valor_unitario_ml,
	smn_tasa_rf,
	smn_moneda_rf,
	mde_valor_unitario_ma,
	mde_monto_bruto_ml,
	mde_monto_bruto_ma,
	mde_monto_disminucion_ml,
	mde_monto_disminucion_ma,
	mde_monto_valor_agregado_ml,
	mde_monto_valor_agregado_ma,
	mde_monto_neto_ml,
	mde_monto_neto_ma,
	mde_estatus,
	mde_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_movimiento_detalle},
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	{d '${def:date}'}
)
