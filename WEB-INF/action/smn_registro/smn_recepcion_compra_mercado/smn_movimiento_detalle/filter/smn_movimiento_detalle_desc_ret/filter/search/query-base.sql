select
	smn_inventario.smn_movimiento_detalle_desc_ret.smn_movimiento_detalle_desc_ret_id,
	smn_base.smn_descuentos_retenciones.dyr_codigo ||'-'|| smn_base.smn_descuentos_retenciones.dyr_descripcion as smn_codigo_descuento_rf,
	smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_base_ml,
	smn_base.smn_descuentos_retenciones.dyr_porcentaje_base as smn_porcentaje_rf
	
from
	smn_inventario.smn_movimiento_detalle_desc_ret
LEFT OUTER JOIN smn_base.smn_descuentos_retenciones on smn_base.smn_descuentos_retenciones.smn_descuentos_retenciones_id = smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf


where
	smn_descuento_retencion_id is not null
	${filter}
order by
		smn_descuento_retencion_id
