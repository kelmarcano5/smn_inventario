select
	smn_inventario.smn_division.smn_division_id, 
	smn_inventario.smn_division.div_codigo as div_codigo_pl0,
	(select smn_base.smn_almacen.alm_codigo|| ' - ' || smn_base.smn_almacen.alm_nombre from  smn_base.smn_almacen  where smn_base.smn_almacen.smn_almacen_id is not null  and smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_sub_division.smn_almacen_rf  order by alm_nombre ) as smn_almacen_rf_combo,
	(select smn_inventario.smn_division.div_codigo|| ' - ' || smn_inventario.smn_division.div_descripcion from  smn_inventario.smn_division  where smn_inventario.smn_division.smn_division_id is not null  and smn_inventario.smn_division.smn_division_id=smn_inventario.smn_sub_division.smn_division_id  order by div_descripcion ) as smn_division_id_combo,
	case
		when smn_inventario.smn_sub_division.sdi_es_ubicacion='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_sub_division.sdi_es_ubicacion='NO' then '${lbl:b_not}'
	end as sdi_es_ubicacion_combo,
	(select smn_base.smn_unidad_medida.unm_codigo|| ' - ' || smn_base.smn_unidad_medida.unm_descripcion from  smn_base.smn_unidad_medida  where smn_base.smn_unidad_medida.smn_unidad_medida_id is not null  and smn_base.smn_unidad_medida.smn_unidad_medida_id=smn_inventario.smn_sub_division.smn_unidad_medida_alto_rf  order by unm_descripcion ) as smn_unidad_medida_alto_rf_combo,
	(select smn_base.smn_unidad_medida.unm_codigo|| ' - ' || smn_base.smn_unidad_medida.unm_descripcion from  smn_base.smn_unidad_medida  where smn_base.smn_unidad_medida.smn_unidad_medida_id is not null  and smn_base.smn_unidad_medida.smn_unidad_medida_id=smn_inventario.smn_sub_division.smn_unidad_medida_ancho_rf  order by unm_descripcion ) as smn_unidad_medida_ancho_rf_combo,
	(select smn_base.smn_unidad_medida.unm_codigo|| ' - ' || smn_base.smn_unidad_medida.unm_descripcion from  smn_base.smn_unidad_medida  where smn_base.smn_unidad_medida.smn_unidad_medida_id is not null  and smn_base.smn_unidad_medida.smn_unidad_medida_id=smn_inventario.smn_sub_division.smn_unidad_medida_prof_rf  order by unm_descripcion ) as smn_unidad_medida_prof_rf_combo,
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
	smn_sub_division_id is not null
	and 	smn_inventario.smn_division.smn_division_id=smn_inventario.smn_sub_division.smn_division_id
	${filter}
order by
		smn_sub_division_id
