UPDATE smn_inventario.smn_despacho_detalle SET
	smn_despacho_id=${fld:smn_despacho_id},
	smn_caracteristica_item_id=${fld:smn_item_rf},
	dde_cantidad_solicitada=${fld:dde_cantidad_solicitada},
	dde_cantidad_despachada=${fld:dde_cantidad_despachada}

WHERE
	smn_despacho_detalle_id=${fld:id}

