UPDATE smn_inventario.smn_ubicacion_detalle SET
	smn_ubicacion_cabecera_id=${fld:smn_ubicacion_cabecera_id},
	smn_divisiones_id=${fld:smn_divisiones_id},
	smn_subdivisiones_id=${fld:smn_subdivisiones_id},
	smn_lote_id=${fld:smn_lote_id},
	ubd_cantidad_inicial=${fld:ubd_cantidad_inicial},
	ubd_entrada=${fld:ubd_entrada},
	ubd_salida=${fld:ubd_salida},
	ubd_cantidad_final=${fld:ubd_cantidad_final},
	ubd_idioma='${def:locale}',
	ubd_usuario='${def:user}',
	ubd_fecha_registro={d '${def:date}'},
	ubd_hora='${def:time}'

WHERE
	smn_ubicacion_detalle_id=${fld:id}

