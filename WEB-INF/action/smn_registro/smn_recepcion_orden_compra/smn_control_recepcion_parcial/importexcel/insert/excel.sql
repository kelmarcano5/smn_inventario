INSERT INTO smn_inventario.smn_control_recepcion_parcial
(
	smn_control_recepcion_id,
	smn_movimiento_cabecera_id,
	smn_orden_compra_rf,
	crp_numero_documento,
	smn_item_id,
	crp_cantidad_recibida,
	crp_fecha_recepcion,
	crp_lote,
	crp_fecha_vencimiento_lote,
	crp_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_control_recepcion_parcial},
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
