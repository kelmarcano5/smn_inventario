SELECT DISTINCT
	smn_movimiento_cabecera_id 
FROM 
	smn_inventario.smn_control_recepcion_parcial
WHERE 
	smn_movimiento_cabecera_id = ${fld:id}