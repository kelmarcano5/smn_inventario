select
	smn_inventario.smn_division.smn_division_id,
	smn_inventario.smn_division.div_codigo as div_codigo_pl0,
select
select
	case
	when smn_inventario.smn_sub_division.sdi_es_ubicacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_sub_division.sdi_es_ubicacion='NO' then '${lbl:b_not}'
	end as sdi_es_ubicacion_combo,
select
select
select
	case
	when smn_inventario.smn_sub_division.sdi_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_sub_division.sdi_estatus='IN' then '${lbl:b_inactive}'
	when smn_inventario.smn_sub_division.sdi_estatus='CU' then '${lbl:b_cuarentena}'
	end as sdi_estatus_combo,
	smn_inventario.smn_sub_division.sdi_estatus,
	smn_inventario.smn_sub_division.sdi_vigencia,
	smn_inventario.smn_sub_division.sdi_idioma,
	smn_inventario.smn_sub_division.sdi_usuario,
	smn_inventario.smn_sub_division.sdi_fecha_registro,
	smn_inventario.smn_sub_division.sdi_hora,
	smn_inventario.smn_sub_division.smn_almacen_rf,
	smn_inventario.smn_sub_division.smn_division_id,
	smn_inventario.smn_sub_division.sdi_codigo,
	smn_inventario.smn_sub_division.sdi_descripcion,
	smn_inventario.smn_sub_division.sdi_es_ubicacion,
	smn_inventario.smn_sub_division.sdi_alto,
	smn_inventario.smn_sub_division.smn_unidad_medida_alto_rf,
	smn_inventario.smn_sub_division.sdi_ancho,
	smn_inventario.smn_sub_division.smn_unidad_medida_ancho_rf,
	smn_inventario.smn_sub_division.sdi_profundidad,
	smn_inventario.smn_sub_division.smn_unidad_medida_prof_rf,
		smn_inventario.smn_sub_division.smn_sub_division_id
from
	smn_inventario.smn_division,
	smn_inventario.smn_sub_division
where
	smn_inventario.smn_division.smn_division_id=smn_inventario.smn_sub_division.smn_division_id
	and
	smn_sub_division_id = ${fld:id}
