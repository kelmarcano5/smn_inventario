UPDATE smn_inventario.smn_tipo_item SET
	tip_codigo=${fld:tip_codigo},
	tip_descripcion=${fld:tip_descripcion},
	tip_estatus=${fld:tip_estatus},
	tip_idioma='${def:locale}',
	tip_usuario='${def:user}',
	tip_fecha_registro={d '${def:date}'},
	tip_hora='${def:time}'

WHERE
	smn_tipo_item_id=${fld:id}

