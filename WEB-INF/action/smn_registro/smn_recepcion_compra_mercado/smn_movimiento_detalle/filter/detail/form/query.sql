select
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	case
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='EN' then '${lbl:b_entry}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='SA' then '${lbl:b_exit}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='AP' then '${lbl:b_fit_price}'
	end as tipo_movimiento,
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	end as bonificacion,
	case
	when smn_inventario.smn_movimiento_detalle.mde_estatus='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='GE' then '${lbl:b_generated}'
	end as estatus,
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_base.smn_item.itm_codigo ||'-'|| smn_base.smn_item.itm_nombre as item,
	smn_base.smn_centro_costo.cco_codigo ||'-'|| smn_base.smn_centro_costo.cco_descripcion_corta as centro_costo,
	smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as unidad_medida,
	smn_inventario.smn_movimiento_detalle.*
	
from
	smn_inventario.smn_movimiento_detalle
	left outer JOIN smn_base.smn_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf
	left outer JOIN smn_base.smn_unidad_medida ON smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf
	LEFT OUTER JOIN smn_base.smn_centro_costo ON smn_inventario.smn_movimiento_detalle.smn_centro_costo_rf = smn_base.smn_centro_costo.smn_centro_costo_id
where
	smn_movimiento_detalle_id = ${fld:id}
