select
select
	smn_base.smn_estructura_organizacional.eor_nombre,
	case
	when smn_inventario.smn_rol.rol_tipo='AL' then '${lbl:b_almacenista}'
	when smn_inventario.smn_rol.rol_tipo='CO' then '${lbl:b_consumidor}'
	when smn_inventario.smn_rol.rol_tipo='AP' then '${lbl:b_approver}'
	end as rol_tipo_combo,
select
select
select
select
	smn_inventario.smn_rol.*
from
	smn_inventario.smn_rol
	left outer join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id = smn_inventario.smn_rol.rol_posicion_estructura_rf
where
	smn_rol_id = ${fld:id}
