INSERT INTO smn_inventario.smn_presentacion
(
	smn_presentacion_id,
	pre_codigo,
	pre_descripcion,
	pre_estatus,
	pre_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_presentacion},
	?,
	?,
	?,
	{d '${def:date}'}
)
