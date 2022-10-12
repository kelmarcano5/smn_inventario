UPDATE smn_inventario.smn_control_recepcion_parcial SET
	smn_movimiento_cabecera_id=${fld:smn_movimiento_cabecera_id},
	smn_orden_compra_rf=${fld:smn_orden_compra_rf},
	crp_numero_documento=${fld:crp_numero_documento},
	smn_item_id=${fld:smn_item_id},
	crp_cantidad_recibida=${fld:crp_cantidad_recibida},
	crp_fecha_recepcion=${fld:crp_fecha_recepcion},
	crp_lote=${fld:crp_lote},
	crp_fecha_vencimiento_lote=${fld:crp_fecha_vencimiento_lote},
	crp_idioma='${def:locale}',
	crp_usuario='${def:user}',
	crp_fecha_registro={d '${def:date}'},
	crp_hora='${def:time}'

WHERE
	smn_control_recepcion_id=${fld:id}

