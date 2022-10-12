SELECT 
	smn_movimiento_cabecera_id AS item
FROM
	smn_inventario.smn_movimiento_cabecera
WHERE
	smn_proveedor_rf = ${fld:smn_proveedor_rf}
	AND
	mca_numero = ${fld:mca_numero}
	AND
	smn_movimiento_cabecera_id != ${fld:id}