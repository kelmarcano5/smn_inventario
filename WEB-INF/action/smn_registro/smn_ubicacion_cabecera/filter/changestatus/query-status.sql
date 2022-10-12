UPDATE smn_inventario.smn_ubicacion_cabecera
SET ubc_estatus = (CASE WHEN (ubc_estatus = 'GE') THEN 'AC'
			ELSE 'GE'
 END),
 	ubc_idioma='${def:locale}',
	ubc_usuario='${def:user}',
	ubc_fecha_registro={d '${def:date}'},
	ubc_hora='${def:time}'
WHERE smn_ubicacion_cabecera_id = ${fld:id}