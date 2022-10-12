SELECT
	smn_inventario.smn_caracteristica_almacen.cal_control_ubicacion
FROM
	smn_inventario.smn_caracteristica_almacen
INNER JOIN
	smn_inventario.smn_movimiento_cabecera
ON
	smn_inventario.smn_caracteristica_almacen.smn_almacen_rf = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}