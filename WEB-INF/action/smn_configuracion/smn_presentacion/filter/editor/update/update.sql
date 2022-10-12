UPDATE smn_inventario.smn_presentacion SET
	pre_codigo=${fld:pre_codigo},
	pre_descripcion=${fld:pre_descripcion},
	pre_estatus=${fld:pre_estatus},
	pre_idioma='${def:locale}',
	pre_usuario='${def:user}',
	pre_fecha_registro={d '${def:date}'},
	pre_hora='${def:time}'

WHERE
	smn_presentacion_id=${fld:id}

