select
	case
	when smn_inventario.smn_principio_activo.pac_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_principio_activo.pac_estatus='IN' then '${lbl:b_inactive}'
	end as pac_estatus,
	smn_inventario.smn_principio_activo.*
from
	smn_inventario.smn_principio_activo
where
	smn_principio_activo_id = ${fld:id}
