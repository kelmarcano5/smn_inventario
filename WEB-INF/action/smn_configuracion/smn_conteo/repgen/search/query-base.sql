select
		smn_inventario.smn_conteo.smn_conteo_id,
	${field}
from
	smn_inventario.smn_conteo
where
		smn_inventario.smn_conteo.smn_conteo_id is not null
	${filter}
	
	
