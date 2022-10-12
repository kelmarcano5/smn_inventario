select
		smn_inventario.smn_transporte.smn_transporte_id,
	${field}
from
	smn_inventario.smn_transporte
where
		smn_inventario.smn_transporte.smn_transporte_id is not null
	${filter}
	
	
