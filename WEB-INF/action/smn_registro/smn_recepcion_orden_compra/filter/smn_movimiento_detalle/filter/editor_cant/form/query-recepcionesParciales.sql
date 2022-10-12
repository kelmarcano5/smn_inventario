SELECT
	crp_numero_documento,
	crp_lote,
	crp_fecha_vencimiento_lote,
	crp_cantidad_recibida
FROM
	smn_inventario.smn_control_recepcion_parcial
INNER JOIN 
	smn_inventario.smn_movimiento_detalle 
	ON
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = smn_inventario.smn_control_recepcion_parcial.smn_movimiento_detalle_id
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:id}
	AND
	smn_inventario.smn_movimiento_detalle.smn_item_rf = smn_inventario.smn_control_recepcion_parcial.smn_item_id