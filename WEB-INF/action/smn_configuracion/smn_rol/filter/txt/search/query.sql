select
	smn_base.smn_estructura_organizacional.eor_nombre,
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
	case
	when smn_inventario.smn_rol.rol_tipo='AL' then '${lbl:b_almacenista}'
	when smn_inventario.smn_rol.rol_tipo='CO' then '${lbl:b_consumidor}'
	when smn_inventario.smn_rol.rol_tipo='AP' then '${lbl:b_approver}'
	end as rol_tipo_combo,
select
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
select
		smn_inventario.smn_rol.smn_rol_id,
	case
	when smn_inventario.smn_rol.rol_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_rol.rol_estatus='IN' then '${lbl:b_inactive}'
	end as rol_estatus_combo,
	smn_inventario.smn_rol.rol_usuario,
	smn_inventario.smn_rol.rol_fecha_registro,
	smn_inventario.smn_rol.rol_hora,
	smn_inventario.smn_rol.smn_usuarios_rf,
	smn_inventario.smn_rol.rol_tipo,
	smn_inventario.smn_rol.smn_corporaciones_rf,
	smn_inventario.smn_rol.smn_entidades_rf,
	smn_inventario.smn_rol.smn_sucursales_rf,
	smn_inventario.smn_rol.smn_areas_servicios_rf,
	smn_inventario.smn_rol.smn_unidades_servicios_rf,
	smn_inventario.smn_rol.rol_posicion_estructura_rf,
	smn_inventario.smn_rol.rol_estatus,
	smn_inventario.smn_rol.rol_vigencia,
	smn_inventario.smn_rol.rol_idioma,
		smn_inventario.smn_rol.smn_rol_id
	
from
	smn_inventario.smn_rol
	left outer join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id = smn_inventario.smn_rol.rol_posicion_estructura_rf
order by
		smn_rol_id
