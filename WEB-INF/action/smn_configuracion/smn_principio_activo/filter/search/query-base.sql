SELECT 
	smn_inventario.smn_principio_activo.smn_principio_activo_id,
	smn_inventario.smn_principio_activo.pac_codigo,
	smn_inventario.smn_principio_activo.pac_descripcion,
	smn_inventario.smn_principio_activo.pac_descripcion_completa,
	case
	when smn_inventario.smn_principio_activo.pac_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_principio_activo.pac_estatus='IN' then '${lbl:b_inactive}'
	end as pac_estatus,
	smn_inventario.smn_principio_activo.pac_fecha_vigencia,
	smn_inventario.smn_principio_activo.pac_idioma,
	smn_inventario.smn_principio_activo.pac_usuario,
	smn_inventario.smn_principio_activo.pac_fecha_registro,
	smn_inventario.smn_principio_activo.pac_hora
FROM 
	smn_inventario.smn_principio_activo
where
	smn_principio_activo_id is not null
	${filter}
order by
		smn_principio_activo_id
