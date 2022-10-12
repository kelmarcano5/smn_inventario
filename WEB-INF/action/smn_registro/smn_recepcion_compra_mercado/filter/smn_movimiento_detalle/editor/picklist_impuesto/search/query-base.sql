select	
	*
from
	smn_base.smn_codigos_impuestos
where
	smn_codigos_impuestos_id is not null
	${filter}
ORDER BY
	imp_codigo