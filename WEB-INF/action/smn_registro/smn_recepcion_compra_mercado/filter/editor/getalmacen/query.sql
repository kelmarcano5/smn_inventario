SELECT 
	smn_base.smn_almacen.smn_almacen_id as id,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as item 
FROM 
	smn_base.smn_almacen 
	LEFT OUTER JOIN smn_seguridad.s_user on smn_seguridad.s_user.userlogin ='${def:user}'
	LEFT OUTER JOIN smn_base.smn_usuarios on smn_base.smn_usuarios.smn_user_rf=smn_seguridad.s_user.user_id
	LEFT OUTER JOIN smn_inventario.smn_rol ON smn_inventario.smn_rol.smn_usuarios_rf = smn_base.smn_usuarios.smn_usuarios_id
	LEFT OUTER JOIN smn_inventario.smn_caracteristica_almacen ON smn_inventario.smn_caracteristica_almacen.smn_almacen_rf = smn_base.smn_almacen.smn_almacen_id
WHERE 
	smn_base.smn_almacen.alm_empresa=(
	SELECT smn_base.smn_entidades.smn_entidades_id
	FROM smn_base.smn_entidades
	WHERE smn_base.smn_entidades.smn_entidades_id=${fld:id}) 
	--AND (smn_inventario.smn_caracteristica_almacen.cal_politica_recepcion = 'FC' OR  smn_inventario.smn_caracteristica_almacen.cal_politica_recepcion = 'AM')
	AND smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen = 'RE'
	AND smn_inventario.smn_rol.rol_tipo = 'AL' 
	AND smn_inventario.smn_rol.rol_estatus = 'AC'
	AND smn_inventario.smn_rol.smn_entidades_rf = ${fld:id}
	