INSERT INTO smn_inventario.smn_tipo_item
(
	smn_tipo_item_id,
	tip_codigo,
	tip_descripcion,
	tip_estatus,
	tip_idioma,
	tip_usuario,
	tip_fecha_registro,
	tip_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_tipo_item},
	${fld:tip_codigo},
	${fld:tip_descripcion},
	${fld:tip_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
