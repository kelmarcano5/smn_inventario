INSERT INTO smn_inventario.smn_movimiento_detalle_impuesto
(
	smn_mov_det_impuesto_id,
	smn_movimiento_detalle_id,
	smn_cod_impuesto_deduc_rf,
	mdi_monto_base,
	smn_porcentaje_impuesto_rf,
	mdi_sustraendo_rf,
	mdi_tipo_movimiento,
	mdi_monto_impuesto_ml,
	smn_moneda,
	smn_tasa,
	mdi_monto_impuesto_ma,
	mdi_idioma,
	mdi_usuario,
	mdi_fecha_registro,
	mdi_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_movimiento_detalle_impuesto},
	${fld:smn_movimiento_detalle_id},
	${fld:smn_cod_impuesto_deduc_rf},
	${fld:mdi_monto_base},
	${fld:smn_porcentaje_impuesto_rf},
	${fld:mdi_sustraendo_rf},
	${fld:mdi_tipo_movimiento},
	${fld:mdi_monto_impuesto_ml},
	${fld:smn_moneda},
	${fld:smn_tasa},
	${fld:mdi_monto_impuesto_ma},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
);

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