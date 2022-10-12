INSERT INTO smn_inventario.smn_ubicacion_cabecera
(
	smn_ubicacion_cabecera_id,
	smn_movimiento_detalle_id,
	smn_caracteristica_almacen_id,
	smn_caracteristica_item_id,
	ubc_estatus,
	ubc_idioma,
	ubc_usuario,
	ubc_fecha_registro,
	ubc_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_ubicacion_cabecera},
	${fld:smn_movimiento_detalle_id},
	${fld:smn_caracteristica_almacen_id},
	${fld:smn_caracteristica_item_id},
	${fld:ubc_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
