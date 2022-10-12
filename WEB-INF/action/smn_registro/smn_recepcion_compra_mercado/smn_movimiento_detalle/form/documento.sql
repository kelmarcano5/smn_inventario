SELECT
	CASE
		WHEN smn_inventario.smn_movimiento_cabecera.mca_numero IS NULL THEN smn_inventario.smn_movimiento_cabecera.mca_recibo||'-'||smn_inventario.smn_documento.doc_nombre
		WHEN smn_inventario.smn_movimiento_cabecera.mca_recibo IS NULL THEN smn_inventario.smn_movimiento_cabecera.mca_numero||'-'||smn_inventario.smn_documento.doc_nombre
	END AS identificador,
	mca_recibo
FROM	
	smn_inventario.smn_documento
	INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:id2}