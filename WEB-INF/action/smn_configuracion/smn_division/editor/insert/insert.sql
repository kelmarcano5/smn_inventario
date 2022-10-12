INSERT INTO smn_inventario.smn_division
(
	smn_division_id,
	div_codigo,
	div_descripcion,
	div_estatus,
	div_vigencia,
	div_idioma,
	div_usuario,
	div_fecha_registro,
	div_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_division},
	${fld:div_codigo},
	${fld:div_descripcion},
	${fld:div_estatus},
	${fld:div_vigencia},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
