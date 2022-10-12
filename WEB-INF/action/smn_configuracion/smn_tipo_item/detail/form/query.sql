select
	case
	when smn_inventario.smn_tipo_item.tip_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_tipo_item.tip_estatus='IN' then '${lbl:b_inactive}'
	end as tip_estatus_combo,
	smn_inventario.smn_tipo_item.*
from
	smn_inventario.smn_tipo_item
where
	smn_tipo_item_id = ${fld:id}
