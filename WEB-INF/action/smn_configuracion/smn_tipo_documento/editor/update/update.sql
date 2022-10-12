UPDATE smn_inventario.smn_tipo_documento SET
	tdc_codigo=${fld:tdc_codigo},
	tdc_nombre=${fld:tdc_nombre},
	tdc_naturaleza=${fld:tdc_naturaleza},
	tdc_vigencia=${fld:tdc_vigencia},
	tdc_estatus=${fld:tdc_estatus},
	tdc_idioma='${def:locale}',
	tdc_usuario='${def:user}',
	tdc_fecha_registro={d '${def:date}'},
	tdc_hora='${def:time}'

WHERE
	smn_tipo_documento_id=${fld:id}

