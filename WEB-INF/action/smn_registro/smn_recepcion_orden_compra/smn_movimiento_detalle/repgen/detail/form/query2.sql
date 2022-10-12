select
		smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id,
	smn_inventario.smn_movimiento_detalle.smn_item_rf,
	smn_inventario.smn_movimiento_detalle.smn_centro_costo_rf,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_fecha_elaboracion_lote,
	smn_inventario.smn_movimiento_detalle.mde_fecha_vencimiento_lote,
	smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento,
	smn_inventario.smn_movimiento_detalle.mde_cantidad,
	smn_inventario.smn_movimiento_detalle.mde_descripcion,
	smn_inventario.smn_movimiento_detalle.mde_es_bonificacion,
	smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf,
	smn_inventario.smn_movimiento_detalle.smn_valor_unitario_ml,
	smn_inventario.smn_movimiento_detalle.smn_tasa_rf,
	smn_inventario.smn_movimiento_detalle.smn_moneda_rf,
	smn_inventario.smn_movimiento_detalle.smn_unitario_ma,
	smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml,
	smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ma,
	smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ml,
	smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ma,
	smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ml,
	smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ma,
	smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml,
	smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma,
	smn_inventario.smn_movimiento_detalle.mde_estatus,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro
from
	smn_inventario.smn_movimiento_detalle 
where
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:id}
