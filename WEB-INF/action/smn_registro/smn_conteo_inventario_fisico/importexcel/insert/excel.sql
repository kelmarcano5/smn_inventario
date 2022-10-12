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
	cfi_fecha_registro
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_conteo_inventario_fisico},
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	{d '${def:date}'}
)
