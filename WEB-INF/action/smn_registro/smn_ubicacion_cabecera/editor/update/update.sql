UPDATE smn_inventario.smn_ubicacion_cabecera SET
	smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id},
	smn_caracteristica_almacen_id=${fld:smn_caracteristica_almacen_id},
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	ubc_estatus=${fld:ubc_estatus},
	ubc_idioma='${def:locale}',
	ubc_usuario='${def:user}',
	ubc_fecha_registro={d '${def:date}'},
	ubc_hora='${def:time}'

WHERE
	smn_ubicacion_cabecera_id=${fld:id}

