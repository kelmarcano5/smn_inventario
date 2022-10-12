select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	${field}
from
	smn_inventario.smn_movimiento_cabecera
where
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id is not null
	${filter}
	
	
