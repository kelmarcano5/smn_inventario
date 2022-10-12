UPDATE smn_inventario.smn_rel_item_almacen SET
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	smn_almacen_rf=${fld:smn_almacen_rf}

WHERE
	smn_rel_item_almacen_id=${fld:id}

