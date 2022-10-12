select
	smn_inventario.smn_conteo.smn_conteo_id,
	case
	when smn_inventario.smn_conteo.con_estatus='AB' then '${lbl:b_open}'
	when smn_inventario.smn_conteo.con_estatus='CE' then '${lbl:b_closed}'
	when smn_inventario.smn_conteo.con_estatus='IN' then '${lbl:b_inactive}'
	end as con_estatus,
	smn_inventario.smn_conteo.con_codigo,
	smn_inventario.smn_conteo.con_descripcion,
	smn_base.smn_almacen.alm_codigo||' - '|| smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_conteo.smn_ubicacion_id,
	aux1.aux_nombres ||' - '|| aux1.aux_apellidos as smn_rol_1_id,
	aux2.aux_nombres ||' - '|| aux2.aux_apellidos as smn_rol_2_id,
	smn_inventario.smn_conteo.con_fecha_registro
	
from
	smn_inventario.smn_conteo
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_conteo.smn_almacen_rf
	inner join smn_inventario.smn_ubicacion_cabecera on smn_inventario.smn_ubicacion_cabecera.smn_ubicacion_cabecera_id =	smn_inventario.smn_conteo.smn_ubicacion_id
	inner join smn_inventario.smn_rol rol1 on rol1.smn_rol_id = smn_inventario.smn_conteo.smn_rol_1_id
	left outer join smn_inventario.smn_rol rol2 on rol2.smn_rol_id = smn_inventario.smn_conteo.smn_rol_2_id
	inner join smn_base.smn_usuarios usu1 on usu1.smn_usuarios_id = rol1.smn_usuarios_rf 
	left outer join smn_base.smn_usuarios usu2 on usu2.smn_usuarios_id = rol2.smn_usuarios_rf 
	inner join smn_base.smn_auxiliar aux1 on aux1.smn_auxiliar_id = usu1.smn_auxiliar_rf
	left outer join smn_base.smn_auxiliar aux2 on aux2.smn_auxiliar_id = usu2.smn_auxiliar_rf

where
	smn_conteo_id = ${fld:id}
