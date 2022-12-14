SELECT
	smn_inventario.smn_caracteristica_almacen.cal_tipo_calculo_costo
FROM
	smn_inventario.smn_caracteristica_almacen
	INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_inventario.smn_caracteristica_almacen.smn_almacen_rf = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	INNER JOIN
	smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id 
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:id2}