UPDATE smn_inventario.smn_control_lote SET
	smn_entidad_rf=${fld:smn_entidad_rf},
	smn_sucursal_rf=${fld:smn_sucursal_rf},
	smn_caracteristica_almacen_id=${fld:smn_caracteristica_almacen_id},
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	col_lote=${fld:col_lote},
	col_fecha_vencimiento=${fld:col_fecha_vencimiento},
	col_cantidad_inicial=${fld:col_cantidad_inicial},
	col_entradas=${fld:col_entradas},
	col_salidas=${fld:col_salidas},
	col_cantidad_final=${fld:col_cantidad_final},
	col_idioma='${def:locale}',
	col_usuario='${def:user}',
	col_fecha_registro={d '${def:date}'},
	col_hora='${def:time}'

WHERE
	smn_control_lote_id=${fld:id}

