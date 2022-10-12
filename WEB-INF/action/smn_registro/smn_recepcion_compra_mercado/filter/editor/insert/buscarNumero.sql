SELECT 
	mca_numero
FROM
	smn_inventario.smn_movimiento_cabecera
WHERE
	smn_proveedor_rf = ${fld:smn_proveedor_rf}
	AND
	mca_numero = ${fld:mca_numero}