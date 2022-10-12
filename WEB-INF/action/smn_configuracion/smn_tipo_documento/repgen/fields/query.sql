select
	*
from
	smn_base.fields	
where
	smn_base.fields.action_root = '${def:actionroot}'
order by
	smn_base.fields.orden asc
	