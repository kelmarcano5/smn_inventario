UPDATE
	smn_inventario.smn_movimiento_cabecera
SET
	mca_recibo = null,
	mca_estatus_proceso = ${fld:mca_estatus_proceso}
WHERE
	smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
	