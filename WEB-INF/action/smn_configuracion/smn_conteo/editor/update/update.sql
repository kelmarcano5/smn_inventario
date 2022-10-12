UPDATE smn_inventario.smn_conteo SET
	con_codigo=${fld:con_codigo},
	con_descripcion=${fld:con_descripcion},
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_ubicacion_id=${fld:smn_ubicacion_id},
	smn_rol_1_id=${fld:smn_rol_1_id},
	smn_rol_2_id=${fld:smn_rol_2_id},
	con_estatus=${fld:con_estatus},
	con_fecha_vigencia=${fld:con_fecha_vigencia},
	con_idioma='${def:locale}',
	con_usuario='${def:user}',
	con_fecha_registro={d '${def:date}'},
	con_hora='${def:time}'

WHERE
	smn_conteo_id=${fld:id}

