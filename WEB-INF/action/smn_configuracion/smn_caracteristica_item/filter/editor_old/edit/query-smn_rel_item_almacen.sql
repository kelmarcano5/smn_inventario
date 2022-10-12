select 
	smn_base.smn_item.itm_nombre as smn_caracteristica_item_id,
	smn_base.smn_almacen.alm_nombre as smn_almacen_rf
from
	smn_inventario.smn_rel_item_almacen
	inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id = smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_caracteristica_item.smn_item_rf
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_rel_item_almacen.smn_almacen_rf
where
	smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id = ${fld:id}