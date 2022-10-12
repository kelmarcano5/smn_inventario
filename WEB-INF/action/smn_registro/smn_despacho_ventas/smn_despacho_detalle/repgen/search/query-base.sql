select
		smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id,
	${field}
from
	smn_inventario.smn_despacho_detalle
where
		smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id is not null
	${filter}
	
	
