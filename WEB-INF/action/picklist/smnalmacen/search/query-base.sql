select	
	smn_base.smn_almacen.*
from
	smn_base.smn_almacen 
where
	smn_almacen_id is not null
	${filter}