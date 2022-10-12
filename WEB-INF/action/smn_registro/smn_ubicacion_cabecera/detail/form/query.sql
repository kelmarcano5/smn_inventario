select
	smn_inventario.smn_ubicacion_cabecera.smn_ubicacion_cabecera_id,
	smn_inventario.smn_movimiento_detalle.mde_descripcion ||' - '|| case 	when mde_tipo_movimiento = 'EN' then '${lbl:b_entrada}'	when mde_tipo_movimiento = 'SA' then '${lbl:b_salida}'	end	 ||' - '|| smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida as smn_movimiento_detalle_id,
	smn_base.smn_almacen.alm_nombre as smn_caracteristica_almacen_id,
	smn_base.smn_item.itm_nombre as smn_caracteristica_item_id,
	case
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='AC' then '${lbl:b_updated}'
	end as ubc_estatus,
	ubc_idioma,
	ubc_usuario,
	smn_inventario.smn_ubicacion_cabecera.ubc_fecha_registro,
	ubc_hora
from
	smn_inventario.smn_ubicacion_cabecera
	inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id=smn_inventario.smn_ubicacion_cabecera.smn_caracteristica_almacen_id
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_caracteristica_almacen.smn_almacen_rf
	inner join smn_inventario.smn_caracteristica_item on  smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=smn_inventario.smn_ubicacion_cabecera.smn_caracteristica_item_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id=smn_inventario.smn_caracteristica_item.smn_item_rf
	inner join smn_inventario.smn_movimiento_detalle on smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = smn_inventario.smn_ubicacion_cabecera.smn_movimiento_detalle_id
where
	smn_ubicacion_cabecera_id = ${fld:id}
