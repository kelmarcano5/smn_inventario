SELECT
	mde_estatus AS item
FROM
	smn_inventario.smn_movimiento_detalle
WHERE
	smn_movimiento_detalle_id = ${fld:id}