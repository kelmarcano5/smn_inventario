UPDATE smn_inventario.smn_movimiento_cabecera SET
	mca_monto_documento_ml = ${fld:mde_monto_documento_ml},
	mca_monto_documento_ma = ${fld:mde_monto_documento_ma},
	mca_monto_diminucion_ml = ${fld:mde_monto_diminucion_ml},
	mca_monto_diminucion_ma = ${fld:mde_monto_diminucion_ma},
 	mca_monto_valor_agregado_ml = ${fld:mde_monto_valor_agregado_ml},
	mca_monto_valor_agregado_ma = ${fld:mde_monto_valor_agregado_ma},
	mca_monto_neto_ml = ${fld:mde_monto_neto_ml},
	mcc_monto_neto_ma = ${fld:mde_monto_neto_ma},
	mca_idioma = '${def:locale}',
  	mca_usuario = '${def:user}',
  	mca_fecha_registro = {d '${def:date}'},
  	mca_hora = '${def:time}'
WHERE
	smn_movimiento_cabecera_id = (select last_value as smn_movimiento_cabecera_id from smn_inventario.seq_smn_movimiento_cabecera)