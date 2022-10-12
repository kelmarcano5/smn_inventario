UPDATE smn_inventario.smn_transporte SET
	tra_codigo=${fld:tra_codigo},
	tra_descripcion_transporte=${fld:tra_descripcion_transporte},
	tra_tipo_transporte=${fld:tra_tipo_transporte},
	smn_activo_rf=${fld:smn_activo_rf},
	smn_proveedor_rf=${fld:smn_proveedor_rf},
	tra_estatus=${fld:tra_estatus},
	tra_vigencia=${fld:tra_vigencia},
	tra_idioma='${def:locale}',
	tra_usuario='${def:user}',
	tra_fecha_registro={d '${def:date}'},
	tra_hora='${def:time}'

WHERE
	smn_transporte_id=${fld:id}

