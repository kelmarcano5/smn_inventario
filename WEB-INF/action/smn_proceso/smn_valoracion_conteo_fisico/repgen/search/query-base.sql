select
		smn_inventario.smn_valoracion_conteo_fisico.smn_valoracion_conteo_fisico_id,
	${field}
from
	smn_inventario.smn_valoracion_conteo_fisico
where
		smn_inventario.smn_valoracion_conteo_fisico.smn_valoracion_conteo_fisico_id is not null
	${filter}
	
	
