SELECT
	smn_despacho_id
FROM
	smn_inventario.smn_despacho
WHERE
	smn_documento_origen_id = ${fld:smn_documento_id_requisicion}
	AND
	des_numero_documento_origen = ${fld:req_numero}