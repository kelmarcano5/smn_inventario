select
		smn_inventario.smn_ubicacion_detalle.smn_ubicacion_detalle_id,
	${field}
from
	smn_inventario.smn_ubicacion_detalle
where
		smn_inventario.smn_ubicacion_detalle.smn_ubicacion_detalle_id is not null
	${filter}
	
	
