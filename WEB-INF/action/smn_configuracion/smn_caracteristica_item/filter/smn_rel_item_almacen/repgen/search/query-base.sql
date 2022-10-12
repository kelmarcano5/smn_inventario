select
		smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id,
	${field}
from
	smn_inventario.smn_rel_item_almacen
where
		smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id is not null
	${filter}
	
	
