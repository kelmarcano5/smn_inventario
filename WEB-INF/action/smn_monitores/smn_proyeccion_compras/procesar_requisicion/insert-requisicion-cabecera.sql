
INSERT INTO smn_compras.smn_requisicion_cabecera
(
	smn_requisicion_cabecera_id,
	smn_cabecera_version_id,
	req_numero,
	req_estatus,
	smn_tipo_documento_id,
	smn_documento_id,
	req_descripcion,
	req_fecha_requerido,
	req_estatus_ruta,
	smn_entidad_id,
	smn_sucursal_id,
	req_idioma,
	req_usuario,
	req_fecha_registro,
	req_hora,
	smn_modulo_rf
)
VALUES
(
	${seq:nextval@smn_compras.seq_smn_requisicion_cabecera},
	${seq:nextval@smn_compras.seq_smn_version_cabecera},
	${seq:nextval@smn_compras.seq_smn_numero_requisicion},
	'SO',
	(select smn_compras.smn_tipo_documento.smn_tipo_documento_id from smn_compras.smn_tipo_documento where smn_compras.smn_tipo_documento.tdc_naturaleza='RE'),
	(select smn_compras.smn_documentos.smn_documentos_id from smn_compras.smn_documentos where smn_compras.smn_documentos.smn_tipo_documento_id = (select smn_compras.smn_tipo_documento.smn_tipo_documento_id from smn_compras.smn_tipo_documento where smn_compras.smn_tipo_documento.tdc_naturaleza='RE') and smn_compras.smn_documentos.dcc_codigo like 'RQA_01%'),
	'Requisicion Generada desde la Proyeccion de Compras',
	{d '${def:date}'},
	'SO',
	${fld:smn_entidad_rf},
	${fld:smn_sucursal_rf},
	9,
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
