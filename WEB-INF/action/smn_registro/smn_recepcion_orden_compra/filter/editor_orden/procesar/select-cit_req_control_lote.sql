SELECT
	cit_req_control_lote
FROM
	smn_inventario.smn_caracteristica_item
WHERE
	smn_item_rf = ${fld:smn_item_rf}
	AND	
	cit_req_control_lote = 'SI'