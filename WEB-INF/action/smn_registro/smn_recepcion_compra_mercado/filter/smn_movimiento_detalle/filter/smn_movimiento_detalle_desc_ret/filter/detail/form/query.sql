select
	smn_inventario.smn_movimiento_detalle_desc_ret.smn_movimiento_detalle_desc_ret_id,
	smn_base.smn_descuento.dct_codigo ||'-'|| smn_base.smn_descuento.dct_nombre as smn_codigo_descuento_rf,
	smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_base_ml,
	smn_base.smn_descuento.dct_porcentaje as smn_porcentaje_rf,
	smn_inventario.smn_movimiento_detalle_desc_ret.*
	
from
	smn_inventario.smn_movimiento_detalle_desc_ret
LEFT OUTER JOIN smn_base.smn_descuento on smn_base.smn_descuento.smn_descuento_id = smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf


where
	smn_movimiento_detalle_desc_ret_id = ${fld:id}
