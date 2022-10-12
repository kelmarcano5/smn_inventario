UPDATE smn_inventario.smn_division SET
	div_codigo=${fld:div_codigo},
	div_descripcion=${fld:div_descripcion},
	div_estatus=${fld:div_estatus},
	div_vigencia=${fld:div_vigencia},
	div_idioma='${def:locale}',
	div_usuario='${def:user}',
	div_fecha_registro={d '${def:date}'},
	div_hora='${def:time}'

WHERE
	smn_division_id=${fld:id}

