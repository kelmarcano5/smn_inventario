select smn_almacen_id as id, alm_codigo  ||' - '|| alm_nombre as item 
from smn_base.smn_almacen 
	inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_almacen_rf=smn_base.smn_almacen.smn_almacen_id
	INNER JOIN smn_inventario.smn_rol ON smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_rol.smn_almacen_rf
	INNER JOIN smn_base.smn_usuarios ON smn_base.smn_usuarios.smn_usuarios_id=smn_inventario.smn_rol.smn_usuarios_rf
	INNER JOIN smn_seguridad.s_user ON smn_base.smn_usuarios.smn_user_rf=smn_seguridad.s_user.user_id
where smn_seguridad.s_user.userlogin='${def:user}'
	
	${filter}
order by smn_almacen_id

