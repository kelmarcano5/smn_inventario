SELECT
	SUM(mdd_monto_descuento_ml) AS sum_monto_descuento_ml
FROM
	smn_inventario.smn_movimiento_detalle_desc_ret
WHERE
	smn_movimiento_detalle_id = ${fld:id2}