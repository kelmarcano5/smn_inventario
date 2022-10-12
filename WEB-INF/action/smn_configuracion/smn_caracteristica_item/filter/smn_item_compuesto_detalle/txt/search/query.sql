select
		smn_inventario.smn_item_compuesto_detalle.smn_item_compuesto_detalle_id,
	case
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='IN' then '${lbl:b_inactive}'
	end as icd_estatus,
	smn_inventario.smn_item_compuesto_detalle.smn_item_id,
	smn_inventario.smn_item_compuesto_detalle.icd_cantidad,
	smn_inventario.smn_item_compuesto_detalle.smn_unidad_medida_rf,
	smn_inventario.smn_item_compuesto_detalle.icd_estatus,
	smn_inventario.smn_item_compuesto_detalle.icd_vigencia,
	smn_inventario.smn_item_compuesto_detalle.icd_fecha_registro
	
from
	smn_inventario.smn_item_compuesto_detalle
