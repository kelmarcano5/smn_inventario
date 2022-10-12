UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_monto_disminucion_ml = ${fld:sum_descuento_ml},
	mde_monto_disminucion_ma = ${fld:sum_descuento_ma},
	mde_monto_valor_agregado_ml = ${fld:sum_impuesto_ml},
	mde_monto_valor_agregado_ma = ${fld:sum_impuesto_ma},
	mde_monto_neto_ml = ${fld:monto_neto_ml_redondeo},
	mde_monto_neto_ma = ${fld:monto_neto_ma_redondeo} 
WHERE
	smn_movimiento_detalle_id = ${fld:id};
	
WITH mde AS (
	SELECT 
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ml) AS total_monto_impuesto_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ma) AS total_monto_impuesto_ma,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ml) AS total_monto_descuento_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ma) AS total_monto_descuento_ma,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml) AS total_monto_bruto_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ma) AS total_monto_bruto_ma,
		case
			when SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml) = 0 then SUM(smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml)
			when SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml) != 0 then SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml)
		end as total_monto_neto_ml,
		case
			when SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma) = 0 then SUM(smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ma)
			when SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma) != 0 then SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma)
		end as total_monto_neto_ma
	FROM 
		smn_inventario.smn_movimiento_detalle
	WHERE
		smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id=${fld:smn_movimiento_cabecera_id}
)

UPDATE smn_inventario.smn_movimiento_cabecera SET
	mca_monto_valor_agregado_ml=mde.total_monto_impuesto_ml,
	mca_monto_valor_agregado_ma=mde.total_monto_impuesto_ma,
	mca_monto_diminucion_ml=mde.total_monto_descuento_ml,
	mca_monto_diminucion_ma = mde.total_monto_descuento_ma,
	mca_monto_documento_ml = mde.total_monto_bruto_ml,
	mca_monto_documento_ma = mde.total_monto_bruto_ma,
	mca_monto_neto_ml=mde.total_monto_neto_ml,
	mcc_monto_neto_ma=mde.total_monto_neto_ma,
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'
FROM 
	mde
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id};