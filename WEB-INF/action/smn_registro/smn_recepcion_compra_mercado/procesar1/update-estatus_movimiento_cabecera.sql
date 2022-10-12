UPDATE 
	smn_inventario.smn_movimiento_cabecera
SET 
	mca_estatus_proceso = 'GE'
WHERE 
 	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=${fld:smn_movimiento_cabecera_id}