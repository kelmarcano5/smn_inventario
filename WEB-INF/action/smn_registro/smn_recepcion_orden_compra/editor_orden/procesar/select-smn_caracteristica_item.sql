SELECT
	smn_caracteristica_item_id,
	cit_req_control_lote
FROM
	smn_inventario.smn_caracteristica_item
WHERE
	smn_item_rf = ${fld:smn_item_rf}