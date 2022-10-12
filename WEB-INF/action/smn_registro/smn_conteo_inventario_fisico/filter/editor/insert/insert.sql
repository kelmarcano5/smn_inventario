INSERT INTO smn_inventario.smn_conteo_inventario_fisico
(
	smn_conteo_inventario_fisico_id,
	smn_conteo_id,
	smn_item_id,
	cfi_cantidad,
	smn_unidad_medida_almacenaje_id,
	cfi_costo,
	smn_auxiliar_1_rf,
	smn_auxiliar_2_rf,
	cfi_estatus,
	cfi_idioma,
	cfi_usuario,
	cfi_fecha_registro,
	cfi_hora,
	cfi_numero_control_lote,
	cfi_fecha_vencimiento
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_conteo_inventario_fisico},
	${fld:smn_conteo_id},
	${fld:smn_item_id},
	${fld:cfi_cantidad},
	${fld:smn_unidad_medida_almacenaje_id},
	${fld:cfi_costo},
	${fld:smn_auxiliar_1_rf},
	${fld:smn_auxiliar_2_rf},
	${fld:cfi_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}',
	${fld:cfi_numero_control_lote},
	${fld:cfi_fecha_vencimiento}

)
