select
		smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id,
	smn_inventario.smn_rel_item_almacen.smn_almacen_rf
from
	smn_inventario.smn_rel_item_almacen 
where
	smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id = ${fld:id}
