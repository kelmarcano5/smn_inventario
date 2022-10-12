SELECT
	smn_inventario.smn_item_compuesto_detalle.smn_item_compuesto_detalle_id,
	smn_inventario.smn_item_compuesto_detalle.smn_caracteristica_item_id,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre AS smn_item_id,
	smn_inventario.smn_item_compuesto_detalle.icd_cantidad,
	smn_base.smn_unidad_medida.unm_codigo ||' - '||  smn_base.smn_unidad_medida.unm_descripcion AS smn_unidad_medida_rf,
	case
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='IN' then '${lbl:b_inactive}'
	end as icd_estatus,
	smn_inventario.smn_item_compuesto_detalle.icd_vigencia,
	smn_inventario.smn_item_compuesto_detalle.icd_idioma,
	smn_inventario.smn_item_compuesto_detalle.icd_usuario,
	smn_inventario.smn_item_compuesto_detalle.icd_fecha_registro,
	smn_inventario.smn_item_compuesto_detalle.icd_hora
FROM
	smn_inventario.smn_item_compuesto_detalle
	INNER JOIN smn_base.smn_unidad_medida ON smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_item_compuesto_detalle.smn_unidad_medida_rf
	INNER JOIN smn_base.smn_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_item_compuesto_detalle.smn_item_id
WHERE
	smn_item_compuesto_detalle_id = ${fld:id}