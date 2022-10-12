select
	*
from
	smn_base.fields	
where
	smn_base.fields.action_root = '/action/smn_monitor/smn_movimiento_cabecera/repgen' --'${def:actionroot}'
order by
	smn_base.fields.orden asc
	