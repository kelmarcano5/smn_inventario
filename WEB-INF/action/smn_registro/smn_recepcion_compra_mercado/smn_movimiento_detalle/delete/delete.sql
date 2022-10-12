WITH mca AS (
	SELECT 
		smn_inventario.smn_movimiento_cabecera.mca_monto_documento_ml AS total_monto_documento_ml,
		smn_inventario.smn_movimiento_cabecera.mca_monto_documento_ma AS total_monto_documento_ma,
		smn_inventario.smn_movimiento_cabecera.mca_monto_diminucion_ml AS total_monto_descuento_ml,
		smn_inventario.smn_movimiento_cabecera.mca_monto_diminucion_ma AS total_monto_descuento_ma,
		smn_inventario.smn_movimiento_cabecera.mca_monto_valor_agregado_ml AS total_impuesto_ml,
		smn_inventario.smn_movimiento_cabecera.mca_monto_valor_agregado_ma AS total_impuesto_ma,
		smn_inventario.smn_movimiento_cabecera.mca_monto_neto_ml AS total_monto_neto_ml,
		smn_inventario.smn_movimiento_cabecera.mcc_monto_neto_ma AS total_monto_neto_ma
	FROM 
		smn_inventario.smn_movimiento_cabecera
	WHERE
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=${fld:id2}
),
mde AS (
	SELECT 
		smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml AS total_monto_bruto_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ma AS total_monto_bruto_ma,
		smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ml AS total_monto_impuesto_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_valor_agregado_ma AS total_monto_impuesto_ma,
		smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ml AS total_monto_descuento_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_disminucion_ma AS total_monto_descuento_ma,
		smn_inventario.smn_movimiento_detalle.mde_monto_neto_ml AS total_monto_neto_ml,
		smn_inventario.smn_movimiento_detalle.mde_monto_neto_ma AS total_monto_neto_ma
	FROM 
		smn_inventario.smn_movimiento_detalle
	WHERE
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id=${fld:id}
)

UPDATE smn_inventario.smn_movimiento_cabecera SET
	mca_monto_documento_ml=mca.total_monto_documento_ml-mde.total_monto_bruto_ml,
	mca_monto_documento_ma=mca.total_monto_documento_ma-mde.total_monto_bruto_ma,
	mca_monto_valor_agregado_ml=mca.total_impuesto_ml-mde.total_monto_impuesto_ml,
	mca_monto_valor_agregado_ma=mca.total_impuesto_ma-mde.total_monto_impuesto_ma,
	mca_monto_diminucion_ml=mca.total_monto_descuento_ml-mde.total_monto_descuento_ml,
	mca_monto_diminucion_ma=mca.total_monto_descuento_ma-mde.total_monto_descuento_ma,
	mca_monto_neto_ml=mca.total_monto_neto_ml-mde.total_monto_neto_ml,
	mcc_monto_neto_ma=mca.total_monto_neto_ma-mde.total_monto_neto_ma,
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'
FROM 
	mca,
	mde
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=${fld:id2};

delete from smn_inventario.smn_movimiento_detalle where smn_movimiento_detalle_id = ${fld:id};
