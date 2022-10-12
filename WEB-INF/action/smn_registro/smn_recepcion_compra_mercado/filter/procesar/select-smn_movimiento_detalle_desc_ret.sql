SELECT
	mdd.*
FROM
	smn_inventario.smn_movimiento_detalle AS mde
INNER JOIN
	smn_inventario.smn_movimiento_detalle_desc_ret AS mdd ON mde.smn_movimiento_detalle_id = mdd.smn_movimiento_detalle_id
WHERE
	mde.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}