select
		smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf
from
		smn_inventario.smn_movimiento_detalle_desc_ret
where
		(smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf) = (${fld:smn_codigo_descuento_rf})
