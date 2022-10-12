select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
	${field}
from
	smn_inventario.smn_conteo_inventario_fisico
where
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id is not null
	${filter}
	
	
