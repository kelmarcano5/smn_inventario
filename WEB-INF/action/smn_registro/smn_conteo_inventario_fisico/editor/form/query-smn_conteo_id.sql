select smn_inventario.smn_conteo.smn_conteo_id as id, smn_inventario.smn_conteo.con_codigo ||' - '|| smn_inventario.smn_conteo.con_descripcion as item from smn_inventario.smn_conteo
inner join smn_inventario.smn_rol on smn_inventario.smn_rol.smn_rol_id = smn_inventario.smn_conteo.smn_rol_1_id
inner join smn_base.smn_usuarios on smn_base.smn_usuarios.smn_usuarios_id = smn_inventario.smn_rol.smn_usuarios_rf
inner join smn_seguridad.s_user on smn_seguridad.s_user.user_id = smn_base.smn_usuarios.smn_user_rf
where smn_seguridad.s_user.userlogin='${def:user}'