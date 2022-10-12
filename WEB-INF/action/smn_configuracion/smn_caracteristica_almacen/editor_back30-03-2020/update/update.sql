UPDATE smn_inventario.smn_caracteristica_almacen SET
	smn_almacen_rf=${fld:smn_almacen_rf},
	cal_tipo_almacen=${fld:cal_tipo_almacen},
	cal_capacidad_almacenaje=${fld:cal_capacidad_almacenaje},
	smn_unidad_medida_rf=${fld:smn_unidad_medida_rf},
	cal_espacio_fisico=${fld:cal_espacio_fisico},
	smn_unidad_medida_espacio_fisico_rf=${fld:smn_unidad_medida_espacio_fisico_rf},
	cal_politica_recepcion=${fld:cal_politica_recepcion},
	cal_estatus=${fld:cal_estatus},
	cal_vigencia_desde=${fld:cal_vigencia_desde},
	cal_vigencia_hasta=${fld:cal_vigencia_hasta},
	cal_idioma='${def:locale}',
	cal_usuario='${def:user}',
	cal_fecha_registro={d '${def:date}'},
	cal_hora='${def:time}'

WHERE
	smn_caracteristica_almacen_id=${fld:id}

