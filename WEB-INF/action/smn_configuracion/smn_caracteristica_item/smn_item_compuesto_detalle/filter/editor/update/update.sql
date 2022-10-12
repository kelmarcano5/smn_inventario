UPDATE smn_inventario.smn_item_compuesto_detalle SET
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	smn_item_id=${fld:smn_item_id},
	icd_cantidad=${fld:icd_cantidad},
	smn_unidad_medida_rf=${fld:smn_unidad_medida_rf},
	icd_estatus=${fld:icd_estatus},
	icd_vigencia=${fld:icd_vigencia},
	icd_idioma='${def:locale}',
	icd_usuario='${def:user}',
	icd_fecha_registro={d '${def:date}'},
	icd_hora='${def:time}'

WHERE
	smn_item_compuesto_detalle_id=${fld:id}

