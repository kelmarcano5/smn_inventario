select	
	*
from
	smn_base.smn_descuentos_retenciones
where
	smn_descuentos_retenciones_id is not null
	${filter}
ORDER BY
	dyr_codigo