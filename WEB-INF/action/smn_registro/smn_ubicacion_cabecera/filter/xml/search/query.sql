select
		smn_inventario.smn_ubicacion_cabecera.smn_ubicacion_cabecera_id,
	case
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='AC' then '${lbl:b_updated}'
	end as ubc_estatus,
	smn_inventario.smn_ubicacion_cabecera.smn_movimiento_detalle_id,
	smn_inventario.smn_ubicacion_cabecera.smn_caracteristica_almacen_id,
	smn_inventario.smn_ubicacion_cabecera.smn_caracteristica_item_id,
	smn_inventario.smn_ubicacion_cabecera.ubc_estatus,
	smn_inventario.smn_ubicacion_cabecera.ubc_fecha_registro
	
from
	smn_inventario.smn_ubicacion_cabecera
