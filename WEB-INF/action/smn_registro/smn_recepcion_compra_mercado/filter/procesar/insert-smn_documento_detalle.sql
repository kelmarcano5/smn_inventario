INSERT INTO smn_cont_financiera.smn_documento_detalle
(
	smn_documento_detalle_id,
	smn_documento_id,
	smn_tipo_elemento_id,
	smn_elemento_id,
	smn_clase_auxiliar_rf,
	smn_auxiliar_rf,
	smn_proyecto_rf,
	smn_centro_costo_rf,
	dod_monto_ml,
	smn_moneda_rf,
	smn_tasa_cambio_rf,
	dod_monto_ma,
	dod_estatus,
	dod_idioma,
	dod_usuario,
 	dod_fecha_registro,
	dod_hora
)
VALUES
(
	nextval('smn_cont_financiera.seq_smn_documento_detalle'), --smn_documento_detalle_id
	${lbl:smn_documento_cabecera_id}, --smn_documento_id
	${fld:smn_tipo_elemento_id}, --smn_tipo_elemento_id
	${fld:elemento}, --smn_elemento_id
	${fld:smn_clase_auxiliar_rf}, --smn_clase_auxiliar_rf
	${fld:smn_auxiliar_rf}, --smn_auxiliar_rf
	0, --smn_proyecto_rf
	${fld:smn_centro_costo_rf_elemento}, --smn_centro_costo_rf
	${fld:mde_monto_bruto_ml}, --dod_monto_ml
	${fld:smn_moneda_rf}, --smn_moneda_rf
	${fld:smn_tasa_rf}, --smn_tasa_cambio_rf
	${fld:mde_monto_bruto_ma}, --dod_monto_ma
	'RE', --dod_estatus
	'${def:locale}', --dod_idioma
  	'${def:user}', --dod_usuario
 	{d '${def:date}'}, --dod_fecha_registro
  	'${def:time}' --dod_hora
)