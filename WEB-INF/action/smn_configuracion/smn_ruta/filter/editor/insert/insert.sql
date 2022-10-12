INSERT INTO smn_inventario.smn_ruta
(
	smn_ruta_id,
	rut_codigo,
	rut_nombre,
	smn_zona_rf,
	rut_posicion_ruta,
	rut_estatus,
	rut_vigencia,
	rut_idioma,
	rut_usuario,
	rut_fecha_registro,
	rut_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_ruta},
	${fld:rut_codigo},
	${fld:rut_nombre},
	${fld:smn_zona_rf},
	${fld:rut_posicion_ruta},
	${fld:rut_estatus},
	${fld:rut_vigencia},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
