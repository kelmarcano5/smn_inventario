UPDATE smn_inventario.smn_documento SET
	smn_tipo_documento_id=${fld:smn_tipo_documento_id},
	doc_codigo=${fld:doc_codigo},
	doc_nombre=${fld:doc_nombre},
	smn_documento_general_rf=${fld:smn_documento_general_rf},
	smn_modulo_origen_rf=${fld:smn_modulo_origen_rf},
	doc_despacho_int_consumo=${fld:doc_despacho_int_consumo},
	doc_despacho_int_transferencia=${fld:doc_despacho_int_transferencia},
	doc_despacho_venta=${fld:doc_despacho_venta},
	doc_secuencia=${fld:doc_secuencia},
	doc_vigencia=${fld:doc_vigencia},
	doc_estatus=${fld:doc_estatus},
	doc_tipo_movimiento=${fld:doc_tipo_movimiento},
	doc_tipo_documento_pago=${fld:doc_tipo_documento_pago},
	doc_idioma='${def:locale}',
	doc_usuario='${def:user}',
	doc_fecha_registro={d '${def:date}'},
	doc_hora='${def:time}'

WHERE
	smn_documento_id=${fld:id}

