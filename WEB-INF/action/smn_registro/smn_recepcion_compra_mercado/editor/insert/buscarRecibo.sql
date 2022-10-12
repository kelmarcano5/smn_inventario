SELECT 
	mca_recibo
FROM
	smn_inventario.smn_movimiento_cabecera
WHERE
	smn_proveedor_rf = ${fld:smn_proveedor_rf}
	AND
	mca_recibo = ${fld:mca_recibo}
	