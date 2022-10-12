SELECT
	smn_base.smn_usuarios.smn_auxiliar_rf
FROM
	smn_base.smn_usuarios
INNER JOIN
	smn_seguridad.s_user ON smn_base.smn_usuarios.smn_user_rf = smn_seguridad.s_user.user_id
INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_seguridad.s_user.userlogin = smn_inventario.smn_movimiento_cabecera.mca_usuario
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id}