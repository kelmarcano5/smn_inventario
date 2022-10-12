select
	case
	when smn_inventario.smn_presentacion.pre_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_presentacion.pre_estatus='IN' then '${lbl:b_inactive}'
	end as pre_estatus,
	smn_inventario.smn_presentacion.*
from
	smn_inventario.smn_presentacion
where
	smn_presentacion_id = ${fld:id}
