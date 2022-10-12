SELECT
	(SELECT dyr_codigo FROM smn_base.smn_descuentos_retenciones WHERE smn_descuentos_retenciones_id=mdd.smn_codigo_descuento_rf) AS codigo,
	(SELECT dyr_descripcion FROM smn_base.smn_descuentos_retenciones WHERE smn_descuentos_retenciones_id=mdd.smn_codigo_descuento_rf) AS descripcion,
	SUM(mdd.mdd_monto_descuento_ml) AS mdd_monto_descuento_ml,
	SUM(mdd.mdd_monto_descuento_ma) AS mdd_monto_descuento_ma,
	mdd.smn_moneda_rf,
	mdd.smn_tasa_rf,
	mde.smn_centro_costo_rf
FROM
	smn_inventario.smn_movimiento_detalle AS mde
INNER JOIN
	smn_inventario.smn_movimiento_detalle_desc_ret AS mdd ON mde.smn_movimiento_detalle_id = mdd.smn_movimiento_detalle_id
WHERE
	mde.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
GROUP BY
	mdd.smn_codigo_descuento_rf,
	mdd.smn_moneda_rf,
	mdd.smn_tasa_rf,
	mde.smn_centro_costo_rf