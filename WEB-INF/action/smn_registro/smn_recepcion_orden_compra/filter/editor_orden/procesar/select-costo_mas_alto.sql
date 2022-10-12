SELECT
	MAX(coi_costo_mas_alto) AS costo_mas_alto
FROM
	smn_inventario.smn_control_item
WHERE
	smn_item_id = ${fld:smn_item_rf}
	