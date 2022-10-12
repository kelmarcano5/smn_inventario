INSERT INTO smn_inventario.smn_item_compuesto_detalle
(
	smn_item_compuesto_detalle_id,
	smn_caracteristica_item_id,
	smn_item_id,
	icd_cantidad,
	smn_unidad_medida_rf,
	icd_estatus,
	icd_vigencia,
	icd_idioma,
	icd_usuario,
	icd_fecha_registro,
	icd_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_item_compuesto_detalle},
	?,
	?,
	?,
	?,
	?,
	?,
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
)
