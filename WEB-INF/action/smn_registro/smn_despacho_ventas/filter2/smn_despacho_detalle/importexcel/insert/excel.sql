INSERT INTO smn_inventario.smn_despacho_detalle
(
	smn_despacho_detalle_id,
	smn_despacho_cabecera_id,
	smn_item_rf,
	dde_cantidad_solicitada,
	dde_cantidad_despachada,
	dde_costo_ml,
	dde_costo_ma,
	dde_motivo,
	smn_usuario_aprobador_rf,
	dde_fecha_aprobacion,
	dde_fecha_cierre,
	dde_estatus_transaccion,
	dde_idioma,
	dde_usuario,
	dde_fecha_registro,
	dde_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_despacho_detalle},
	?,
	?,
	?,
	?,
	?,
	?,
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
