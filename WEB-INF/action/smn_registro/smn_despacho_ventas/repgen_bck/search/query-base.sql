select
		smn_inventario.smn_despacho.smn_despacho_id,
	${field}
from
	smn_inventario.smn_despacho
where
		smn_inventario.smn_despacho.smn_despacho_id is not null
	${filter}
	
	
