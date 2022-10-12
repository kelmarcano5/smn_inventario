select
	smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id,
	${field}
from
	smn_inventario.smn_caracteristica_almacen
where
	smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id is not null
	${filter}
	
	
