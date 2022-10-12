UPDATE smn_inventario.smn_movimiento_detalle_desc_ret SET
	smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id},
	smn_codigo_descuento_rf=${fld:smn_codigo_descuento_rf},
	mdd_monto_base_ml=${fld:mdd_monto_base_ml},
	smn_porcentaje_rf=${fld:smn_porcentaje_rf},
	mdd_monto_descuento_ml=${fld:mdd_monto_descuento_ml},
	smn_moneda_rf=${fld:smn_moneda_rf},
	smn_tasa_rf=${fld:smn_tasa_rf},
	mdd_monto_base_ma=${fld:mdd_monto_base_ma},
	mdd_monto_descuento_ma=${fld:mdd_monto_descuento_ma},
	mdd_idioma='${def:locale}',
	mdd_usuario='${def:user}',
	mdd_fecha_registro='${def:date}',
	mdd_hora='${def:time}'

WHERE
	smn_movimiento_detalle_desc_ret_id=${fld:id};

WITH impuestos AS (
	SELECT 
		CASE
			WHEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ml) IS NULL THEN 0
			WHEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ml) IS NOT NULL THEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ml)
		END AS total_monto_impuesto_ml,
		CASE
			WHEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ma) IS NULL THEN 0
			WHEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ma) IS NOT NULL THEN SUM(smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ma)
		END AS total_monto_impuesto_ma
	FROM 
		smn_inventario.smn_movimiento_detalle_impuesto
	WHERE
		smn_inventario.smn_movimiento_detalle_impuesto.smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id}
)

UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_monto_valor_agregado_ml=impuestos.total_monto_impuesto_ml,
	mde_monto_valor_agregado_ma=impuestos.total_monto_impuesto_ma,
	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'
FROM 
	impuestos
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id};

-- **** ACTUALIZA LOS MONTOS DE DESCUENTOS Y RETENCIONES EN LA TABLA -> smn_compras.smn_orden_compra_detalle****

WITH descuentos AS (
	SELECT 
		CASE
			WHEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ml) IS NULL THEN 0
			WHEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ml) IS NOT NULL THEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ml)
		END AS total_monto_descuento_ml,
		CASE
			WHEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ma) IS NULL THEN 0
			WHEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ma) IS NOT NULL THEN SUM(smn_inventario.smn_movimiento_detalle_desc_ret.mdd_monto_descuento_ma)
		END AS total_monto_descuento_ma
	FROM 
		smn_inventario.smn_movimiento_detalle_desc_ret
	WHERE
		smn_inventario.smn_movimiento_detalle_desc_ret.smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id}
)

UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_monto_disminucion_ml=descuentos.total_monto_descuento_ml,
	mde_monto_disminucion_ma=descuentos.total_monto_descuento_ma,
	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'
FROM 
	descuentos
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id};	

-- **** ACTUALIZA LOS MONTOS NETO EN LA TABLA -> smn_compras.smn_orden_compra_detalle****

WITH mde AS (
	SELECT 
		smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ma,
		smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ma,
		smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ma
	FROM 
		smn_inventario.smn_movimiento_detalle
	WHERE
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id}
)

UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_monto_neto_ml=mde.mde_monto_bruto_ml+mde.mde_monto_valor_agregado_ml-mde.mde_monto_disminucion_ml,
	mde_monto_neto_ma=mde.mde_monto_bruto_ma+mde.mde_monto_valor_agregado_ma-mde.mde_monto_disminucion_ma,
	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'
FROM 
	mde
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id};

-- **** ACTUALIZA LOS MONTOS EN LA TABLA -> smn_compras.smn_orden_compra_cabecera****

WITH mde AS (
	SELECT 
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ml) AS total_monto_impuesto_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ma) AS total_monto_impuesto_ma,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ml) AS total_monto_descuento_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ma) AS total_monto_descuento_ma,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml) AS total_monto_neto_ml,
		SUM(smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma) AS total_monto_neto_ma
	FROM 
		smn_inventario.smn_movimiento_detalle
	WHERE
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id}
)

UPDATE smn_inventario.smn_movimiento_cabecera SET
	mca_monto_valor_agregado_ml=mde.total_monto_impuesto_ml,
	mca_monto_valor_agregado_ma=mde.total_monto_impuesto_ma,
	mca_monto_diminucion_ml=mde.total_monto_descuento_ml,
	mca_monto_diminucion_ma = mde.total_monto_descuento_ma,
	mca_monto_neto_ml=mde.total_monto_neto_ml,
	mcc_monto_neto_ma=mde.total_monto_neto_ma,
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'
FROM 
	mde
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = (SELECT smn_movimiento_cabecera_id FROM smn_inventario.smn_movimiento_detalle WHERE smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id});