INSERT INTO smn_inventario.smn_documento
(
	smn_documento_id,
	smn_tipo_documento_id,
	doc_codigo,
	doc_nombre,
	smn_documento_general_rf,
	smn_modulo_origen_rf,
	doc_despacho_int_consumo,
	doc_despacho_int_transferencia,
	doc_despacho_venta,
	doc_secuencia,
	doc_vigencia,
	doc_estatus,
	doc_tipo_movimiento,
	doc_tipo_documento_pago,
	doc_idioma,
	doc_usuario,
	doc_fecha_registro,
	doc_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_documento},
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
	?,
	?,
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
