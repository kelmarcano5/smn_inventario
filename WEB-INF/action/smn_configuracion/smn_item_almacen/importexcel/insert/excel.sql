INSERT INTO smn_inventario.smn_item_almacen
(
	smn_item_almacen_id,
	smn_item_rf,
	smn_almacen_rf,
	ria_estatus,
	ria_vigencia,
	ria_idioma,
	ria_usuario,
	ria_fecha_registro,
	ria_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_item_almacen},
	?,
	?,
	?,
	?,
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
