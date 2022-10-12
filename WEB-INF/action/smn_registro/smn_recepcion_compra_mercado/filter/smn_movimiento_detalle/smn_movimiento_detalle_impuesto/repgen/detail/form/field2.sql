select
	smn_base.fields.alias,
	smn_base.fields.align,
	smn_base.fields.colname,
	smn_base.fields.width_px,
	smn_base.fields.market as col,
	smn_base.fields.orden,
	case
	when format is not null then '${fld:' || market || format
	else '${fld:' || market || '}'
	end as market,
	smn_base.fields.format,
	upper (smn_base.fields.type) as type
from
	smn_base.fields
where
	smn_base.fields.field_id in ${fields}
	and
	smn_base.fields.action_root = ''
order by
		smn_base.fields.orden asc
