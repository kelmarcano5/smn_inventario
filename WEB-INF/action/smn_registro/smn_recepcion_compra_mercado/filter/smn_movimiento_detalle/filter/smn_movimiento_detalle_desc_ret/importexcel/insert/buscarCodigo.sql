select
		smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf
from
		smn_inventario.smn_movimiento_detalle_desc_ret
where
		upper(smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf) = upper(${fld:smn_codigo_descuento_rf})
