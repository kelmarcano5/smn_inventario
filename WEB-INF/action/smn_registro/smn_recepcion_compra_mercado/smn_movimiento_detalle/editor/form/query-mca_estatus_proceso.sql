SELECT
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso AS item
FROM
	smn_inventario.smn_movimiento_detalle
INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:id}