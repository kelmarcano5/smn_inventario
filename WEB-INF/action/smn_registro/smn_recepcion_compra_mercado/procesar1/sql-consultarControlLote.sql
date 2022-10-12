SELECT 
	* 
FROM 
	smn_inventario.smn_control_lote
WHERE 
	smn_caracteristica_item_id = ${fld:smn_caracteristica_item_id} 
	AND
	smn_caracteristica_almacen_id = ${fld:smn_caracteristica_almacen_id}
	AND
	col_lote = ${fld:mde_lote_string}
ORDER BY col_fecha_registro DESC LIMIT 1
