SELECT
	cit_tipo_costo
FROM
	smn_inventario.smn_caracteristica_item
WHERE
	smn_item_rf = ${fld:smn_item_rf}