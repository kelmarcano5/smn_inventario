select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id,
	smn_inventario.smn_conteo_inventario_fisico.smn_item_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_cantidad,
	smn_inventario.smn_conteo_inventario_fisico.smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_costo,
	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_1_rf,
	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_2_rf,
	smn_inventario.smn_conteo_inventario_fisico.cfi_estatus,
	smn_inventario.smn_conteo_inventario_fisico.cfi_fecha_registro
from
	smn_inventario.smn_conteo_inventario_fisico 
where
	smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id = ${fld:id}
