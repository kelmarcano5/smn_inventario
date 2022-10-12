INSERT INTO smn_inventario.smn_tipo_item
(
	smn_tipo_item_id,
	tip_codigo,
	tip_descripcion,
	tip_estatus,
	tip_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_tipo_item},
	?,
	?,
	?,
	{d '${def:date}'}
)
