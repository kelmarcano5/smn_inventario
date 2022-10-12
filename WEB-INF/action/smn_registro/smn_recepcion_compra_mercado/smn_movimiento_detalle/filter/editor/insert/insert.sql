INSERT INTO smn_inventario.smn_movimiento_detalle
(
	smn_movimiento_detalle_id,
	smn_movimiento_cabecera_id,
	smn_item_rf,
	smn_centro_costo_rf,
	mde_lote,
	mde_fecha_vencimiento_lote,
	mde_tipo_movimiento,
	mde_cantidad_recibida,
	mde_descripcion,
	mde_es_bonificacion,
	smn_unidad_medida_rf,
	mde_valor_unitario_ml,
	smn_tasa_rf,
	smn_moneda_rf,
	mde_valor_unitario_ma,
	mde_monto_bruto_ml,
	mde_monto_bruto_ma,
	mde_monto_disminucion_ml,
	mde_monto_disminucion_ma,
	mde_monto_valor_agregado_ml,
	mde_monto_valor_agregado_ma,
	mde_monto_neto_ml,
	mde_monto_neto_ma,
	mde_estatus,
	mde_idioma,
	mde_usuario,
	mde_fecha_registro,
	mde_hora
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_movimiento_detalle},
	${fld:smn_movimiento_cabecera_id},
	${fld:smn_item_rf},
	${fld:smn_centro_costo_rf},
	${fld:mde_lote},
	${fld:mde_fecha_vencimiento_lote},
	${fld:mde_tipo_movimiento},
	${fld:mde_cantidad_recibida},
	${fld:mde_descripcion},
	${fld:mde_es_bonificacion},
	${fld:smn_unidad_medida_rf},
	${fld:mde_valor_unitario_ml},
	${fld:smn_tasa_rf},
	${fld:smn_moneda_rf},
	${fld:mde_valor_unitario_ma},
	${fld:mde_monto_bruto_ml},
	${fld:mde_monto_bruto_ma},
	${fld:mde_monto_disminucion_ml},
	${fld:mde_monto_disminucion_ma},
	${fld:mde_monto_valor_agregado_ml},
	${fld:mde_monto_valor_agregado_ma},
	${fld:mde_monto_neto_ml},
	${fld:mde_monto_neto_ma},
	${fld:mde_estatus},
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
);

-- **** ACTUALIZA LOS MONTOS EN LA TABLA -> smn_compras.smn_orden_compra_cabecera****

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