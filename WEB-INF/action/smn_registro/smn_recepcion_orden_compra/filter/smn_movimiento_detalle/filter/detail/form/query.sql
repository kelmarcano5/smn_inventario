select
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	case
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='EN' then '${lbl:b_entry}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='SA' then '${lbl:b_exit}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='AP' then '${lbl:b_fit_price}'
	end as tipo_movimiento,
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='S' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='N' then '${lbl:b_not}'
	end as bonificacion,
	CASE
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='GE' THEN '${lbl:b_generated}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='RE' THEN '${lbl:b_registry}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='RC' THEN '${lbl:b_received}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='PR' THEN '${lbl:b_received_partial}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='CE' THEN '${lbl:b_closed}'
	END AS estatus,
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre as item,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as unidad_medida,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro,
	smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml,
	smn_base.smn_centro_costo.cco_codigo||'-'||smn_base.smn_centro_costo.cco_descripcion_corta AS centro_costo,
	smn_base.smn_tasas_de_cambio.tca_tasa_cambio AS tasa,
	smn_base.smn_monedas.mon_codigo||'-'||smn_base.smn_monedas.mon_nombre AS moneda,
	smn_inventario.smn_movimiento_detalle.*
	
from
	smn_inventario.smn_movimiento_detalle
	INNER JOIN smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
	left outer JOIN smn_base.smn_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf
	left outer JOIN smn_base.smn_unidad_medida ON smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf
	LEFT OUTER JOIN smn_base.smn_centro_costo ON smn_base.smn_centro_costo.smn_centro_costo_id = smn_inventario.smn_movimiento_detalle.smn_centro_costo_rf
	LEFT OUTER JOIN smn_base.smn_tasas_de_cambio ON smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_movimiento_cabecera.smn_tasa_rf
	LEFT OUTER JOIN smn_base.smn_monedas ON smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_movimiento_cabecera.smn_moneda_rf
where
	smn_movimiento_detalle_id = ${fld:id}
