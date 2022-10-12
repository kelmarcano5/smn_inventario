select
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	${field}
from
	smn_inventario.smn_movimiento_detalle
where
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id is not null
	${filter}
	
	
