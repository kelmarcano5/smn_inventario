select
		smn_inventario.smn_item_compuesto_detalle.smn_item_compuesto_detalle_id,
	${field}
from
	smn_inventario.smn_item_compuesto_detalle
where
		smn_inventario.smn_item_compuesto_detalle.smn_item_compuesto_detalle_id is not null
	${filter}
	
	
