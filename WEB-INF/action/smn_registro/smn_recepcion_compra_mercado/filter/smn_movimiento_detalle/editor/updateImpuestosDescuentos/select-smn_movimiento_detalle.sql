SELECT
	mde_monto_bruto_ml,
	mde_monto_bruto_ma,
	mde_monto_neto_ml,
	mde_monto_neto_ma,
	smn_moneda_rf,
	smn_tasa_rf,
	smn_movimiento_cabecera_id
FROM
	smn_inventario.smn_movimiento_detalle
WHERE
	smn_movimiento_detalle_id = ${fld:id}