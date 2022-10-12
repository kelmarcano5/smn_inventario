select
	case
	when smn_inventario.smn_division.div_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_division.div_estatus='IN' then '${lbl:b_inactive}'
	when smn_inventario.smn_division.div_estatus='CU' then '${lbl:b_cuarentena}'
	end as div_estatus_combo,
	smn_inventario.smn_division.div_codigo,
	smn_inventario.smn_division.div_descripcion,
	smn_inventario.smn_division.div_estatus,
	smn_inventario.smn_division.div_vigencia,
	smn_inventario.smn_division.div_idioma,
	smn_inventario.smn_division.div_usuario,
	smn_inventario.smn_division.div_fecha_registro,
	smn_inventario.smn_division.div_hora,
		smn_inventario.smn_division.smn_division_id
	
from
	smn_inventario.smn_division
where
	smn_division_id is not null
	${filter}
order by
		smn_division_id
