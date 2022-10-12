INSERT INTO smn_impuestos.smn_impuestos
(
  smn_impuestos_id,
  smn_entidades_id,
  smn_auxiliar_sucursales_id,
  imp_documento_origen,
  imp_modulo_origen_id,
  smn_clase_auxiliar_id,
  smn_auxiliar_id,
  imp_rif,
  smn_tipo_documento_impuestos_id,
  imp_numero_documento,
  imp_num_control_fiscal,
  imp_fecha,
  imp_base_imponible_porcentaje,
  imp_base_imponible_monto,
  imp_base_excenta,
  smn_codigo_impuesto_rf,
  imp_porcentaje,
  imp_monto_impuesto,
  imp_sustraendo,
  imp_monto_neto_impuesto,
  imp_descripcion,
  imp_cantidad,
  imp_cod_doc_afectado,
  imp_num_doc_afectado,
  imp_num_control_doc_afectado,
  imp_fecha_doc_afectado,
  imp_cod_imp_afectado,
  imp_monto_base_doc_afectado,
  imp_numero_comprob_impuesto,
  imp_fecha_comprob_impuesto,
  imp_tipo_comprob_contable,
  imp_num_comprob_contable,
  imp_fecha_comprob_contable,
  imp_estatus_comprobante,
  imp_estatus,
  imp_fecha_declaracion,
  imp_num_declaracion,
  smn_documento_id,
  imp_idioma,
  imp_usuario_id,
  imp_fecha_registro,
  imp_hora,
  imp_control_final,
  imp_num_factura_final,
  imp_caja,
  imp_num_control_fiscal_inicial1,
  imp_num_control_fiscal_final1,
  imp_num_control_fiscal_inicial2,
  imp_num_control_fiscal_final2,
  imp_num_planilla_importacion,
  smn_caja_fiscal_rf,
  smn_monedas_rf,
  smn_tasa_cambio_rf,
  imp_monto_neto_imp_ma
)
VALUES
(
	nextval('smn_impuestos.seq_smn_impuestos'), --smn_impuestos_id,
  	${fld:smn_entidad_rf}, --smn_entidades_id,
 	${fld:smn_sucursal_rf}, --smn_auxiliar_sucursales_id,
  	${fld:smn_documento_id}, --imp_documento_origen,
  	${fld:smn_modulos_id}, --imp_modulo_origen_id,
  	${fld:smn_clase_auxiliar_rf_proveedor}, --smn_clase_auxiliar_id,
  	${fld:smn_auxiliar_rf_proveedor}, --smn_auxiliar_id,
  	${fld:aux_rif}, --imp_rif,
  	null, --smn_tipo_documento_impuestos_id,
  	${fld:mca_numero}, --imp_numero_documento,
  	0, --imp_num_control_fiscal,
  	${fld:mca_fecha_recibida}, --imp_fecha,
  	${fld:porcentaje_base}, --imp_base_imponible_porcentaje,
  	${fld:mdi_monto_base}, --imp_base_imponible_monto,
  	${fld:monto_base_excenta_ml}, --imp_base_excenta,
  	${fld:smn_cod_impuesto_deduc_rf}, --smn_codigo_impuesto_rf,
  	${fld:porcentaje_calculo}, --imp_porcentaje,
  	${fld:mde_monto_bruto_ml}, --imp_monto_impuesto,
  	${fld:monto_sustraendo}, --imp_sustraendo,
  	${fld:mde_monto_neto_ml}, --imp_monto_neto_impuesto,
  	${fld:mde_descripcion}, --imp_descripcion,
  	1, --imp_cantidad
  	null, --imp_cod_doc_afectado
  	null, --imp_num_doc_afectado
  	null, --imp_num_control_doc_afectado
  	null, --imp_fecha_doc_afectado
  	null, --imp_cod_imp_afectado
  	null, --imp_monto_base_doc_afectado
  	null, --imp_numero_comprob_impuesto
  	null, --imp_fecha_comprob_impuesto
  	null, --imp_tipo_comprob_contable
  	null, --imp_num_comprob_contable
  	null, --imp_fecha_comprob_contable
  	'SC', --imp_estatus_comprobante
  	'RE', --imp_estatus
  	null, --imp_fecha_declaracion
  	null, --imp_num_declaracion
  	null, --smn_documento_id
  	'${def:locale}', --imp_idioma
  	'${def:user}', --imp_usuario_id
  	{d '${def:date}'}, --imp_fecha_registro
  	'${def:time}', --imp_hora
  	null, --imp_control_final
  	${fld:mca_numero}, --imp_num_factura_final
  	null, --imp_caja
  	null, --imp_num_control_fiscal_inicial1
  	null, --imp_num_control_fiscal_final1
  	null, --imp_num_control_fiscal_inicial2
  	null, --imp_num_control_fiscal_final2
  	null, --imp_num_planilla_importacion
  	null, --smn_caja_fiscal_rf
  	${fld:smn_moneda_rf}, --smn_moneda
  	${fld:smn_tasa_rf}, --smn_tasa
  	${fld:mde_monto_bruto_ma} --mdi_monto_impuesto_ma
)