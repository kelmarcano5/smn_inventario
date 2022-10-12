select DISTINCT smn_base.smn_auxiliar.smn_auxiliar_id as id, smn_base.smn_auxiliar.aux_codigo|| ' - ' ||smn_base.smn_auxiliar.aux_descripcion as item from smn_base.smn_auxiliar
left outer join smn_inventario.smn_conteo_inventario_fisico on smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_1_rf = smn_base.smn_auxiliar.smn_auxiliar_id
inner join smn_base.smn_usuarios on smn_base.smn_usuarios.smn_auxiliar_rf =  smn_base.smn_auxiliar.smn_auxiliar_id
inner join smn_seguridad.s_user on smn_seguridad.s_user.user_id = smn_base.smn_usuarios.smn_user_rf
where smn_seguridad.s_user.userlogin='${def:user}'