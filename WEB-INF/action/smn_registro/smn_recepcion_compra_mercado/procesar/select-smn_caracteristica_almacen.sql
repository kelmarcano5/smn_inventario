SELECT
	smn_caracteristica_almacen_id,
	smn_auxiliar_rf
FROM
	smn_inventario.smn_caracteristica_almacen
WHERE
	smn_almacen_rf = ${fld:smn_almacen_rf}