INSERT INTO smn_inventario.smn_tipo_documento
(
	smn_tipo_documento_id,
	tdc_codigo,
	tdc_nombre,
	tdc_naturaleza,
	tdc_vigencia,
	tdc_estatus,
	tdc_idioma,
	tdc_usuario,
	tdc_fecha_registro,
	tdc_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_tipo_documento},
	${fld:tdc_codigo},
	${fld:tdc_nombre},
	${fld:tdc_naturaleza},
	${fld:tdc_vigencia},
	${fld:tdc_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
