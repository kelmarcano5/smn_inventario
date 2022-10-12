SELECT
	(cct_fecha_hasta+1) AS item
FROM
	smn_inventario.smn_control_cierre_transaccion
WHERE
	smn_inventario.smn_control_cierre_transaccion.smn_entidad_rf = ${fld:entidad_id}
	AND
	smn_inventario.smn_control_cierre_transaccion.smn_almacen_rf = ${fld:almacen_id}
	AND
	smn_inventario.smn_control_cierre_transaccion.smn_transaccion_id = 'SA'
	