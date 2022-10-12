select	
	smn_compras.smn_lineas.*
from
	smn_compras.smn_lineas 
where
	smn_lineas_id is not null
	${filter}