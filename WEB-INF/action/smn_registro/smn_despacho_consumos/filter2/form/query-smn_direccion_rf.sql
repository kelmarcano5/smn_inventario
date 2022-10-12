select DISTINCT
	smn_base.smn_zona.smn_zona_id as id, 
	smn_base.smn_zona.zon_codigo||' - '|| smn_base.smn_zona.zon_descripcion as item 
from 
	smn_base.smn_direccion 
	inner join smn_base.smn_rel_auxiliar_direccion on smn_base.smn_rel_auxiliar_direccion.smn_direccion_id = smn_base.smn_direccion.smn_direccion_id
	inner join smn_base.smn_zona on smn_base.smn_zona.smn_zona_id = smn_base.smn_rel_auxiliar_direccion.smn_zona_rf
order by id, item asc