UPDATE smn_inventario.smn_movimiento_cabecera SET
	smn_modulo_rf=${fld:smn_modulo_rf},
	smn_documento_rf=${fld:smn_documento_rf},
	mca_numero=${fld:mca_numero},
	smn_proveedor_rf=${fld:smn_proveedor_rf},
	smn_orden_compra_rf=${fld:smn_orden_compra_rf},
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_sucursal_rf=${fld:smn_sucursal_rf},
	smn_entidad_rf=${fld:smn_entidad_rf},
	mca_recibo=${fld:mca_recibo},
	mca_monto_documento_ml=${fld:mca_monto_documento_ml},
	mca_monto_documento_ma=${fld:mca_monto_documento_ma},
	mca_monto_diminucion_ml=${fld:mca_monto_diminucion_ml},
	mca_monto_diminucion_ma=${fld:mca_monto_diminucion_ma},
	mca_monto_valor_agregado_ml=${fld:mca_monto_valor_agregado_ml},
	mca_monto_valor_agregado_ma=${fld:mca_monto_valor_agregado_ma},
	mca_monto_neto_ml=${fld:mca_monto_neto_ml},
	mcc_monto_neto_ma=${fld:mcc_monto_neto_ma},
	smn_moneda_rf=${fld:smn_moneda_rf},
	smn_tasa_rf=${fld:smn_tasa_rf},
	mca_fecha_operacion=${fld:mca_fecha_operacion},
	mca_estatus_proceso=${fld:mca_estatus_proceso},
	mca_estatus_financiero=${fld:mca_estatus_financiero},
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'

WHERE
	smn_movimiento_cabecera_id=${fld:id}

