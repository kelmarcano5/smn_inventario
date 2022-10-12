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
	con_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_conteo},
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	{d '${def:date}'}
)
