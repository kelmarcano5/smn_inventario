select	
	smn_base.smn_codigos_impuestos.*
from
	smn_base.smn_codigos_impuestos 
where
	smn_codigos_impuestos_id is not null
	${filter}