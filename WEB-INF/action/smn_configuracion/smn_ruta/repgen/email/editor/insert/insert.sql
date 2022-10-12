INSERT INTO smn_base.smn_mensajes
(
	smn_mensajes_id,
	msj_asunto,
	msj_texto,
	msj_idioma,
	msj_usuario,
	msj_fecha_registro,
	msj_hora
)
VALUES
(
	${seq:currval@smn_base.seq_smn_mensajes},
	${fld:msj_asunto},
	${fld:msj_texto},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
