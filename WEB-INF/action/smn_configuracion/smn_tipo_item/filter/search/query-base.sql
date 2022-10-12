select
	case
	when smn_inventario.smn_tipo_item.tip_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_tipo_item.tip_estatus='IN' then '${lbl:b_inactive}'
	end as tip_estatus_combo,
	smn_inventario.smn_tipo_item.tip_codigo,
	smn_inventario.smn_tipo_item.tip_descripcion,
	smn_inventario.smn_tipo_item.tip_estatus,
	smn_inventario.smn_tipo_item.tip_fecha_registro,
		smn_inventario.smn_tipo_item.smn_tipo_item_id
	
from
	smn_inventario.smn_tipo_item
where
	smn_tipo_item_id is not null
	${filter}
order by
		smn_tipo_item_id
