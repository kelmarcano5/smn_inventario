UPDATE smn_inventario.smn_principio_activo SET
	pac_codigo=${fld:pac_codigo},
	pac_descripcion=${fld:pac_descripcion},
	pac_descripcion_completa=${fld:pac_descripcion_completa},
	pac_estatus=${fld:pac_estatus},
	pac_fecha_vigencia=${fld:pac_fecha_vigencia},
	pac_idioma='${def:locale}',
	pac_usuario='${def:user}',
	pac_fecha_registro={d '${def:date}'},
	pac_hora='${def:time}'

WHERE
	smn_principio_activo_id=${fld:id}

