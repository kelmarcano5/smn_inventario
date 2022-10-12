select
		smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id,
select
		smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id,
	case
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='PR' then '${lbl:b_produccion}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='DE' then '${lbl:b_despacho}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='VI' then '${lbl:b_ventas_internet}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='PP' then '${lbl:b_productos_procesos}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='TR' then '${lbl:b_transferencia}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='RE' then '${lbl:b_recepcion}'
	when smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen='AL' then '${lbl:b_almacenaje}'
	end as cal_tipo_almacen_combo,
select
		smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id,
select
		smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id,
	case
	when smn_inventario.smn_caracteristica_almacen.cal_politica_recepcion='FC' then '${lbl:b_factura}'
	when smn_inventario.smn_caracteristica_almacen.cal_politica_recepcion='NE' then '${lbl:b_nota_entrada}'
	end as cal_politica_recepcion_combo,
	case
	when smn_inventario.smn_caracteristica_almacen.cal_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_caracteristica_almacen.cal_estatus='IN' then '${lbl:b_inactive}'
	when smn_inventario.smn_caracteristica_almacen.cal_estatus='CU' then '${lbl:b_cuarentena}'
	end as cal_estatus_combo,
	smn_inventario.smn_caracteristica_almacen.cal_usuario,
	smn_inventario.smn_caracteristica_almacen.cal_fecha_registro,
	smn_inventario.smn_caracteristica_almacen.cal_hora,
	smn_inventario.smn_caracteristica_almacen.smn_almacen_rf,
	smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen,
	smn_inventario.smn_caracteristica_almacen.cal_capacidad_almacenaje,
	smn_inventario.smn_caracteristica_almacen.smn_unidad_medida_rf,
	smn_inventario.smn_caracteristica_almacen.cal_espacio_fisico,
	smn_inventario.smn_caracteristica_almacen.smn_unidad_medida_espacio_fisico_rf,
	smn_inventario.smn_caracteristica_almacen.cal_politica_recepcion,
	smn_inventario.smn_caracteristica_almacen.cal_estatus,
	smn_inventario.smn_caracteristica_almacen.cal_vigencia_desde,
	smn_inventario.smn_caracteristica_almacen.cal_vigencia_hasta,
	smn_inventario.smn_caracteristica_almacen.cal_idioma,
		smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id
	
from
	smn_inventario.smn_caracteristica_almacen
order by
		smn_caracteristica_almacen_id
