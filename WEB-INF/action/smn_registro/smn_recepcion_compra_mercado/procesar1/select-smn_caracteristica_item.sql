SELECT
	smn_caracteristica_item_id,
	cit_req_control_lote,
	smn_unidad_medida_base_rf
FROM
	smn_inventario.smn_caracteristica_item
WHERE
	smn_item_rf = ${fld:smn_item_rf}