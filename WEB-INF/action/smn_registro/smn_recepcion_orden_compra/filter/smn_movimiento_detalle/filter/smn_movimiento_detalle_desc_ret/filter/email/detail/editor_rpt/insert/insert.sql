INSERT INTO smn_base.smn_respuestas
(
	smn_respuestas_id,
	smn_mensajes_id,
	rsp_texto,
	rsp_idioma,
	rsp_usuario,
	rsp_fecha_registro,
	rsp_hora
)
VALUES
(
	${seq:currval@smn_base.seq_smn_respuestas},
	${fld:mensaje_id2},
	${fld:rsp_texto},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
