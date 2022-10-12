SELECT 
	smn_inventario.smn_movimiento_detalle.*, 
	smn_inventario.smn_movimiento_detalle.smn_item_rf AS item_rf, 
	cit_tipo_costo
FROM 
	smn_inventario.smn_movimiento_detalle 
INNER JOIN
	smn_inventario.smn_caracteristica_item 
ON 
	smn_inventario.smn_caracteristica_item.smn_item_rf=smn_inventario.smn_movimiento_detalle.smn_item_rf
WHERE 
	smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
