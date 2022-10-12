SELECT
	smn_descuento_retencion_id
FROM
	smn_inventario.smn_movimiento_detalle_desc_ret
WHERE
	smn_movimiento_detalle_id = ${fld:id}
	AND
	smn_codigo_descuento_rf = ${fld:smn_descuentos_retenciones_id}