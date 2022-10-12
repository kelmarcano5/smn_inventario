INSERT INTO smn_inventario.smn_conteo
(
	smn_conteo_id,
	con_codigo,
	con_descripcion,
	smn_almacen_rf,
	smn_ubicacion_id,
	smn_rol_1_id,
	smn_rol_2_id,
	con_estatus,
	con_fecha_vigencia,
	con_idioma,
	con_usuario,
	con_fecha_registro,
	con_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_conteo},
	${fld:con_codigo},
	${fld:con_descripcion},
	${fld:smn_almacen_rf},
	${fld:smn_ubicacion_id},
	${fld:smn_rol_1_id},
	${fld:smn_rol_2_id},
	${fld:con_estatus},
	{d '${def:date}'},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
