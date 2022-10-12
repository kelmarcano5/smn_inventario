INSERT INTO smn_base.smn_contactos
(
	smn_contactos_id,
	cnt_nombres,
	cnt_apellidos,
	cnt_email,
	cnt_idioma,
	cnt_usuario,
	cnt_fecha_registro,
	cnt_hora
)
VALUES
(
	${seq:currval@smn_base.seq_smn_contactos},
	${fld:cnt_nombres2},
	${fld:cnt_apellidos2},
	${fld:cnt_email2},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
