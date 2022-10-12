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
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre as smn_item_rf,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as smn_unidad_medida_rf,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro,
	smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml,
	smn_inventario.smn_movimiento_cabecera.mca_recibo 
FROM
	smn_inventario.smn_movimiento_detalle
LEFT OUTER JOIN smn_base.smn_item ON (smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf)
LEFT OUTER JOIN smn_base.smn_unidad_medida ON smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf
LEFT OUTER JOIN smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id
WHERE
	smn_movimiento_detalle_id is not null 
	AND
	smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id=${fld:id2}
	${filter}
ORDER BY mde_fecha_registro DESC


