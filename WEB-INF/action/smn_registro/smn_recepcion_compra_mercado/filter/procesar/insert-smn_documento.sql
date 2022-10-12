INSERT INTO smn_cont_financiera.smn_documento
(
	smn_documento_id,
	smn_modulo_rf,
	smn_entidades_rf,
	smn_sucursales_rf,
	smn_documentos_generales_rf,
	smn_tipo_documento_id,
	doc_numero_documento,
	smn_clase_auxiliar_rf,
	smn_auxiliar_rf,
	smn_centro_costo_rf,
	doc_fecha_doc,
	doc_fecha_rec,
	doc_fecha_vcto,
	doc_monto_ml,
	doc_monto_ma,
	doc_estatus,
	smn_tipo_comprobante_id,
	doc_descripcion,
	doc_idioma,
	doc_usuario,
 	doc_fecha_registro,
	doc_hora
)
VALUES
(
	nextval('smn_cont_financiera.seq_smn_documento'), --smn_documento_id
	${fld:smn_modulos_id}, --smn_modulo_rf
	${fld:smn_entidad_rf}, --smn_entidades_rf
	${fld:smn_sucursal_rf}, --smn_sucursales_rf
	${fld:smn_documento_general_rf}, --smn_documentos_generales_rf
	${fld:smn_tipo_documento_id}, --smn_tipo_documento_id
	${fld:mca_numero}, --doc_numero_documento
	${fld:smn_clase_auxiliar_rf}, --smn_clase_auxiliar_rf
	${fld:smn_auxiliar_rf}, --smn_auxiliar_rf
	${fld:smn_centro_costo_rf}, --smn_centro_costo_rf
	${fld:mca_fecha_recibida}, --doc_fecha_doc
	${fld:mca_fecha_recibida}, --doc_fecha_rec
	${fld:doc_fecha_vcto}, --doc_fecha_vcto
	0.0, --doc_monto_ml
	0.0, --doc_monto_ma
	'RE', --doc_estatus
	${fld:mcc_tipo_comp}, --smn_tipo_comprobante_id
	${fld:doc_descripcion}, --doc_descripcion
	'${def:locale}', --doc_idioma
  	'${def:user}', --doc_usuario
 	{d '${def:date}'}, --doc_fecha_registro
  	'${def:time}' --doc_hora
)

RETURNING smn_documento_id;