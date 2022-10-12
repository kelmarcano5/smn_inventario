UPDATE smn_inventario.smn_conteo_inventario_fisico SET
	smn_conteo_id=${fld:smn_conteo_id},
	smn_item_id=${fld:smn_item_id},
	cfi_cantidad=${fld:cfi_cantidad},
	smn_unidad_medida_almacenaje_id=${fld:smn_unidad_medida_almacenaje_id},
	cfi_costo=${fld:cfi_costo},
	smn_auxiliar_1_rf=${fld:smn_auxiliar_1_rf},
	smn_auxiliar_2_rf=${fld:smn_auxiliar_2_rf},
	cfi_estatus=${fld:cfi_estatus},
	cfi_idioma='${def:locale}',
	cfi_usuario='${def:user}',
	cfi_fecha_registro={d '${def:date}'},
	cfi_hora='${def:time}',
	cfi_numero_control_lote=${fld:cfi_numero_control_lote},
	cfi_fecha_vencimiento=${fld:cfi_fecha_vencimiento}

WHERE
	smn_conteo_inventario_fisico_id=${fld:id}

