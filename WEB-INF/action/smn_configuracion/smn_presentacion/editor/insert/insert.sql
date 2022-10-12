INSERT INTO smn_inventario.smn_presentacion
(
	smn_presentacion_id,
	pre_codigo,
	pre_descripcion,
	pre_estatus,
	pre_idioma,
	pre_usuario,
	pre_fecha_registro,
	pre_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_presentacion},
	${fld:pre_codigo},
	${fld:pre_descripcion},
	${fld:pre_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
