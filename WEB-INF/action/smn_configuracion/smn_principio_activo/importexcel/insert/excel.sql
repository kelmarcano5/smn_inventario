INSERT INTO smn_inventario.smn_principio_activo
(
	smn_principio_activo_id,
	pac_codigo,
	pac_descripcion,
	pac_descripcion_completa,
	pac_estatus,
	pac_fecha_vigencia,
	pac_idioma,
	pac_usuario,
	pac_fecha_registro,
	pac_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_principio_activo},
	?,
	?,
	?,
	?,
	?,
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
