INSERT INTO smn_pagos.smn_nota_entrada
(
	smn_nota_entrada_op_id,
	smn_nota_entrada_rf,
	smn_documento_inventario_rf,
	smn_orden_compra_rf,
	smn_documento_rf,
	smn_numero_documento_rf,
	smn_clase_rf,
	smn_proveedor_id,
	smn_usuario_rf,
	nep_monto_ml,
	nep_monto_ma,
	smn_moneda_rf,
	nep_monto_usado_ml,
	nep_monto_usado_ma,
	nep_monto_saldo_ml,
	nep_monto_saldo_ma,
	ocp_estatus,
	nep_idioma,
	nep_usuario,
	nep_fecha_registro,
	nep_hora,
	smn_orden_pago_id
)
VALUES
(
	nextval('smn_pagos.smn_nota_entrada'); --smn_nota_entrada_op_id
	${fld:smn_movimiento_cabecera_id}, --smn_nota_entrada_rf
	${fld:smn_documento_id}, --smn_documento_inventario_rf
	0, --smn_orden_compra_rf
	${fld:smn_documento_general_rf}, --smn_documento_rf
	${fld:mca_numero}, --smn_numero_documento_rf
	${fld:smn_clase_auxiliar_rf}, --smn_clase_rf
	${fld:smn_proveedor_rf}, --smn_proveedor_id
	${fld:smn_usuario_rf}, --smn_usuario_rf
	${fld:mca_monto_documento_ml}, --nep_monto_ml
	${fld:mca_monto_documento_ma}, --nep_monto_ma
	${fld:smn_moneda_rf}, --smn_moneda_rf
	0.0, --nep_monto_usado_ml
	0.0, --nep_monto_usado_ma
	0.0, --nep_monto_saldo_ml
	0.0, --nep_monto_saldo_ma
	'GE', --ocp_estatus
	'${def:locale}', --nep_idioma
	'${def:user}', --nep_usuario
	{d '${def:date}'}, --nep_fecha_registro
	'${def:time}', --nep_hora
	${fld:mca_numero} --smn_orden_pago_id
)