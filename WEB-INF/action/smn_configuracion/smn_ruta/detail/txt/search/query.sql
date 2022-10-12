select
	case
	when smn_inventario.smn_ruta.rut_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_ruta.rut_estatus='IN' then '${lbl:b_inactive}'
	end as rut_estatus,
	smn_inventario.smn_ruta.*
from
	smn_inventario.smn_ruta
where
	smn_ruta_id = ${fld:id}
