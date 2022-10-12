SELECT
	mca_estatus_proceso AS item
FROM
	smn_inventario.smn_movimiento_cabecera
WHERE
	smn_movimiento_cabecera_id = ${fld:id}