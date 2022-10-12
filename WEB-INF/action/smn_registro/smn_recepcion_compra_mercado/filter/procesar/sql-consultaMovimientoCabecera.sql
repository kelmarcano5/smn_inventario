SELECT * FROM smn_inventario.smn_movimiento_cabecera WHERE 
	smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id} 
	AND
	mca_estatus_proceso = 'RE'