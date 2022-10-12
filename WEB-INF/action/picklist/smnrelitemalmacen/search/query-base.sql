select	
	smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id,
	smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id
from
	smn_inventario.smn_rel_item_almacen
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_rel_item_almacen.smn_almacen_rf
where
	smn_rel_item_almacen_id is not null
	${filter}