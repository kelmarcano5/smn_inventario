select 
	smn_zona_id as id, 
	zon_codigo  ||' - '|| zon_descripcion as item 
from smn_base.smn_zona 
	order by smn_zona_id