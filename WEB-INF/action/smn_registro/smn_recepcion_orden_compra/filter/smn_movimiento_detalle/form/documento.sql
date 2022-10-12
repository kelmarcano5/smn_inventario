SELECT
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen||'-'||smn_inventario.smn_documento.doc_nombre AS identificador
FROM	
	smn_inventario.smn_documento
	INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:id2}