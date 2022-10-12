select
		smn_inventario.smn_principio_activo.smn_principio_activo_id,
	${field}
from
	smn_inventario.smn_principio_activo
where
		smn_inventario.smn_principio_activo.smn_principio_activo_id is not null
	${filter}
	
	
