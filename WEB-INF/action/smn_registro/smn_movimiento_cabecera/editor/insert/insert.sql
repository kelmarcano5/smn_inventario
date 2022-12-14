INSERT INTO smn_inventario.smn_movimiento_cabecera
(
	smn_movimiento_cabecera_id,
	smn_modulo_rf,
	smn_documento_rf,
	mca_numero,
	smn_proveedor_rf,
	smn_orden_compra_rf,
	smn_almacen_rf,
	smn_sucursal_rf,
	smn_entidad_rf,
	mca_recibo,
	mca_monto_documento_ml,
	mca_monto_documento_ma,
	mca_monto_diminucion_ml,
	mca_monto_diminucion_ma,
	mca_monto_valor_agregado_ml,
	mca_monto_valor_agregado_ma,
	mca_monto_neto_ml,
	mcc_monto_neto_ma,
	smn_moneda_rf,
	smn_tasa_rf,
	mca_fecha_operacion,
	mca_estatus_proceso,
	mca_estatus_financiero,
	mca_idioma,
	mca_usuario,
	mca_fecha_registro,
	mca_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_movimiento_cabecera},
	${fld:smn_modulo_rf},
	${fld:smn_documento_rf},
	${fld:mca_numero},
	${fld:smn_proveedor_rf},
	${fld:smn_orden_compra_rf},
	${fld:smn_almacen_rf},
	${fld:smn_sucursal_rf},
	${fld:smn_entidad_rf},
	${fld:mca_recibo},
	${fld:mca_monto_documento_ml},
	${fld:mca_monto_documento_ma},
	${fld:mca_monto_diminucion_ml},
	${fld:mca_monto_diminucion_ma},
	${fld:mca_monto_valor_agregado_ml},
	${fld:mca_monto_valor_agregado_ma},
	${fld:mca_monto_neto_ml},
	${fld:mcc_monto_neto_ma},
	${fld:smn_moneda_rf},
	${fld:smn_tasa_rf},
	${fld:mca_fecha_operacion},
	${fld:mca_estatus_proceso},
	${fld:mca_estatus_financiero},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
