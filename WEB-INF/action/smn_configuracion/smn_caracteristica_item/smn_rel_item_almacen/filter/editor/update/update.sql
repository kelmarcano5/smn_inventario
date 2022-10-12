UPDATE smn_inventario.smn_rel_item_almacen SET
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	smn_almacen_rf=${fld:smn_almacen_rf},
	ria_cantidad_max=${fld:ria_cantidad_min},
	ria_cantidad_min=${fld:ria_cantidad_max},
	ria_punto_reorden=${fld:ria_punto_reorden},
	ria_cantidad_seguridad=${fld:ria_cantidad_seguridad}
WHERE
	smn_rel_item_almacen_id=${fld:id}

