select
		smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id,
	${field}
from
	smn_inventario.smn_caracteristica_item
where
		smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id is not null
	${filter}
	
	
