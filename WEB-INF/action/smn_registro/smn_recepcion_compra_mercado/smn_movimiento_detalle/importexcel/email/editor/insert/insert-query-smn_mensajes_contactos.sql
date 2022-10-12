INSERT INTO smn_base.smn_mensajes_contactos
(
	smn_mensajes_contactos_id,
	smn_mensajes_id,
	smn_contactos_id
)
VALUES
(
	${seq:nextval@smn_base.seq_smn_mensajes_contactos},
	${seq:currval@smn_base.seq_smn_mensajes},
	${fld:smn_contactos_id}
)
