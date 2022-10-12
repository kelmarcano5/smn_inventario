select
	case
	when smn_inventario.smn_presentacion.pre_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_presentacion.pre_estatus='IN' then '${lbl:b_inactive}'
	end as pre_estatus,
	smn_inventario.smn_presentacion.pre_codigo,
	smn_inventario.smn_presentacion.pre_descripcion,
	smn_inventario.smn_presentacion.pre_fecha_registro,
		smn_inventario.smn_presentacion.smn_presentacion_id
	
from
	smn_inventario.smn_presentacion
where
	smn_presentacion_id is not null
	${filter}
order by
		smn_presentacion_id
