UPDATE smn_inventario.smn_item_almacen SET
	smn_item_rf=${fld:smn_item_rf},
	smn_almacen_rf=${fld:smn_almacen_rf},
	ria_estatus=${fld:ria_estatus},
	ria_vigencia=${fld:ria_vigencia},
	ria_idioma='${def:locale}',
	ria_usuario='${def:user}',
	ria_fecha_registro={d '${def:date}'},
	ria_hora='${def:time}'

WHERE
	smn_item_almacen_id=${fld:id}

