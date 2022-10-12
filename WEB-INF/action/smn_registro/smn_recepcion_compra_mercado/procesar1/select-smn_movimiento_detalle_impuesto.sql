SELECT
	smn_cod_impuesto_deduc_rf,
	(SELECT imp_codigo FROM smn_base.smn_codigos_impuestos WHERE smn_codigos_impuestos_id=mdi.smn_cod_impuesto_deduc_rf) AS codigo,
	(SELECT imp_descripcion FROM smn_base.smn_codigos_impuestos WHERE smn_codigos_impuestos_id=mdi.smn_cod_impuesto_deduc_rf) AS descripcion,
	(SELECT imp_porcentaje_base FROM smn_base.smn_codigos_impuestos WHERE smn_codigos_impuestos_id=mdi.smn_cod_impuesto_deduc_rf) AS porcentaje_base,
	(SELECT imp_porcentaje_calculo FROM smn_base.smn_codigos_impuestos WHERE smn_codigos_impuestos_id=mdi.smn_cod_impuesto_deduc_rf) AS porcentaje_calculo,
	(SELECT imp_monto_sustraendo FROM smn_base.smn_codigos_impuestos WHERE smn_codigos_impuestos_id=mdi.smn_cod_impuesto_deduc_rf) AS monto_sustraendo,
	SUM(mdi.mdi_monto_impuesto_ml) AS mdi_monto_impuesto_ml,
	SUM(mdi.mdi_monto_impuesto_ma) AS mdi_monto_impuesto_ma,
	SUM(mdi.mdi_monto_base) AS mdi_monto_base,
	SUM(mde.mde_monto_bruto_ml) AS mde_monto_bruto_ml,
	mdi.smn_moneda,
	mdi.smn_tasa,
	mde.smn_centro_costo_rf,
	mde.mde_descripcion
FROM
	smn_inventario.smn_movimiento_detalle AS mde
INNER JOIN
	smn_inventario.smn_movimiento_detalle_impuesto AS mdi ON mde.smn_movimiento_detalle_id = mdi.smn_movimiento_detalle_id
WHERE
	mde.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
GROUP BY
	mdi.smn_cod_impuesto_deduc_rf,
	mdi.smn_moneda,
	mdi.smn_tasa,
	mde.smn_centro_costo_rf,
	mde.mde_descripcion
