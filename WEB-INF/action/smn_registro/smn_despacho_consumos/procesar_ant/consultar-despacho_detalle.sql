SELECT smn_inventario.smn_despacho_detalle.*, smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id as smn_item_rf FROM smn_inventario.smn_despacho_detalle WHERE smn_despacho_id = ${fld:smn_despacho_id}