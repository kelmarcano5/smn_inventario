SELECT 
	smn_moneda_rf,
	smn_tasa_rf,
	smn_item_rf,
	SUM(mde_monto_bruto_ml) AS mde_monto_bruto_ml,
	SUM(mde_monto_bruto_ma) AS mde_monto_bruto_ma
FROM 
	smn_inventario.smn_movimiento_detalle 
WHERE 
	smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
	AND
	mde_estatus = 'PE'
GROUP BY smn_moneda_rf,smn_tasa_rf,smn_item_rf