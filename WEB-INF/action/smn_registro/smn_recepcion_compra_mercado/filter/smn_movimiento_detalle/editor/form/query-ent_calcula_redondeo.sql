SELECT
	smn_base.smn_entidades.ent_calcula_redondeo
FROM
	smn_base.smn_entidades
	INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:id2}