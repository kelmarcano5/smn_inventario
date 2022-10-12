SELECT
	smn_mov_det_impuesto_id
FROM
	smn_inventario.smn_movimiento_detalle_impuesto
WHERE
	smn_movimiento_detalle_id = ${fld:id}
	AND
	smn_cod_impuesto_deduc_rf = ${fld:smn_codigos_impuestos_id}