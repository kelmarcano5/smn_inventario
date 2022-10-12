select
		smn_inventario.smn_principio_activo.smn_principio_activo_id,
	case
	when smn_inventario.smn_principio_activo.pac_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_principio_activo.pac_estatus='IN' then '${lbl:b_inactive}'
	end as pac_estatus,
	smn_inventario.smn_principio_activo.pac_codigo,
	smn_inventario.smn_principio_activo.pac_descripcion,
	smn_inventario.smn_principio_activo.pac_descripcion_completa,
	smn_inventario.smn_principio_activo.pac_estatus,
	smn_inventario.smn_principio_activo.pac_fecha_vigencia,
	smn_inventario.smn_principio_activo.pac_fecha_registro
	
from
	smn_inventario.smn_principio_activo
