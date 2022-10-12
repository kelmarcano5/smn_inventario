UPDATE smn_inventario.smn_ruta SET
	rut_codigo=${fld:rut_codigo},
	rut_nombre=${fld:rut_nombre},
	smn_zona_rf=${fld:smn_zona_rf},
	rut_posicion_ruta=${fld:rut_posicion_ruta},
	rut_estatus=${fld:rut_estatus},
	rut_vigencia=${fld:rut_vigencia},
	rut_idioma='${def:locale}',
	rut_usuario='${def:user}',
	rut_fecha_registro={d '${def:date}'},
	rut_hora='${def:time}'

WHERE
	smn_ruta_id=${fld:id}

