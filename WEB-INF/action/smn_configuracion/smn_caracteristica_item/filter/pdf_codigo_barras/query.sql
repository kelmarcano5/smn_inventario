SELECT 
	smn_base.smn_item.itm_codigo,
	smn_base.smn_item.itm_nombre,
	{d '${def:date}'} AS fecha_actual
FROM
	smn_inventario.smn_caracteristica_item
	INNER JOIN
	smn_base.smn_item ON smn_inventario.smn_caracteristica_item.smn_item_rf = smn_base.smn_item.smn_item_id
where 
	smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=${fld:id}