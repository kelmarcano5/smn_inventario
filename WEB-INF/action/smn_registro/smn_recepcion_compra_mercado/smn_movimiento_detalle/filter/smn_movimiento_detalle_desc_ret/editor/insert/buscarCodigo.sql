select
		smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf
from
		smn_inventario.smn_movimiento_detalle_desc_ret
		INNER JOIN
		smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = smn_inventario.smn_movimiento_detalle_desc_ret.smn_movimiento_detalle_id
where
		(smn_inventario.smn_movimiento_detalle_desc_ret.smn_codigo_descuento_rf) = (${fld:smn_codigo_descuento_rf})
		AND
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id}