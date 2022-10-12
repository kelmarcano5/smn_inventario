UPDATE 
	smn_inventario.smn_movimiento_cabecera
SET 
	mca_monto_documento_ml = ${fld:total_monto_bruto_ml},
  	mca_monto_documento_ma = ${fld:total_monto_bruto_ma},
  	mca_monto_diminucion_ml = ${fld:total_monto_disminucion_ml},
  	mca_monto_diminucion_ma = ${fld:total_monto_disminucion_ma},
  	mca_monto_valor_agregado_ml = ${fld:total_monto_valor_agregado_ml},
  	mca_monto_valor_agregado_ma = ${fld:total_monto_valor_agregado_ma},
  	mca_monto_neto_ml = ${fld:total_monto_neto_ml},
  	mcc_monto_neto_ma = ${fld:total_monto_neto_ma}
WHERE 
 	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=${fld:smn_movimiento_cabecera_id}
