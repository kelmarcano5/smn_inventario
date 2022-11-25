select
	*
from
	smn_base.fields	
where
	smn_base.fields.action_root = '/action/smn_registro/smn_despacho/repgen'
order by
	smn_base.fields.orden asc
	