SELECT 
	mca_recibo
FROM 
	smn_inventario.smn_movimiento_cabecera
WHERE 
	mca_recibo = ${fld:mca_recibo}
	AND
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id != ${fld:id}