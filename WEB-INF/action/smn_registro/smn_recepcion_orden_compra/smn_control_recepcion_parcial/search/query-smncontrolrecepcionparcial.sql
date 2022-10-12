SELECT
	CASE
		WHEN smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso = 'PR' THEN '${lbl:b_received_partial}'
	END AS mca_estatus_proceso,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen AS smn_orden_compra_rf,
	smn_inventario.smn_control_recepcion_parcial.crp_numero_documento,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre AS smn_item_id,
	smn_inventario.smn_control_recepcion_parcial.crp_cantidad_recibida,
	smn_inventario.smn_control_recepcion_parcial.crp_fecha_recepcion,
	smn_inventario.smn_control_recepcion_parcial.crp_lote,
	smn_inventario.smn_control_recepcion_parcial.crp_fecha_vencimiento_lote,
	smn_inventario.smn_control_recepcion_parcial.crp_fecha_registro
FROM
	smn_inventario.smn_movimiento_cabecera
LEFT OUTER JOIN
	smn_inventario.smn_control_recepcion_parcial ON smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = smn_inventario.smn_control_recepcion_parcial.smn_movimiento_cabecera_id
LEFT OUTER JOIN
	smn_base.smn_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_control_recepcion_parcial.smn_item_id
WHERE
	smn_inventario.smn_control_recepcion_parcial.smn_movimiento_cabecera_id = ${fld:id2}
