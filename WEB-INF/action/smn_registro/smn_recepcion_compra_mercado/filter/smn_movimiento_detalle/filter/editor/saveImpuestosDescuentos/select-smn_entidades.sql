SELECT
	smn_base.smn_entidades.ent_calcula_redondeo
FROM
	smn_base.smn_entidades
	INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	INNER JOIN
	smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id 
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:id}