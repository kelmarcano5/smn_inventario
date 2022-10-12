SELECT 
	smn_base.smn_item.smn_item_id as id,
	smn_base.smn_item.itm_codigo||'-'||smn_base.smn_item.itm_nombre as item 
FROM smn_base.smn_item
inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_item_rf = smn_base.smn_item.smn_item_id
inner join smn_inventario.smn_rel_item_almacen on smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id = smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id

WHERE smn_inventario.smn_rel_item_almacen.smn_almacen_rf=${fld:id}