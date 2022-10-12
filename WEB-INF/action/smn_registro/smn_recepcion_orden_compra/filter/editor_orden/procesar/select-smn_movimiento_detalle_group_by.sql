SELECT 
	smn_item_rf,
	smn_moneda_rf,
	smn_tasa_rf,
	(SELECT itm_codigo FROM smn_base.smn_item WHERE smn_item_id=smn_inventario.smn_movimiento_detalle.smn_item_rf) AS codigo,
	(SELECT itm_nombre FROM smn_base.smn_item WHERE smn_item_id=smn_inventario.smn_movimiento_detalle.smn_item_rf) AS descripcion,
	SUM(mde_monto_bruto_ml) AS mde_monto_bruto_ml,
	SUM(mde_monto_bruto_ma) AS mde_monto_bruto_ma,
	SUM(mde_monto_neto_ml) AS mde_monto_neto_ml,
	SUM(mde_monto_neto_ma) AS mde_monto_neto_ma
FROM 
	smn_inventario.smn_movimiento_detalle 
WHERE 
	smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}
AND
	mde_estatus IN ('PE','PR') 
		
GROUP BY smn_moneda_rf,smn_tasa_rf,smn_item_rf