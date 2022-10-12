UPDATE smn_inventario.smn_movimiento_cabecera SET
	mca_estatus_proceso='RB',
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'

WHERE
	smn_movimiento_cabecera_id=${fld:id}

