select
	(select mod_codigo || ' - ' || mod_nombre from  smn_base.smn_modulos  where smn_modulos_id is not null  and smn_modulos_id=smn_inventario.smn_despacho.smn_modulo_rf  order by smn_modulos_id ) as smn_modulo_rf_combo,
	(select doc_codigo||' - '||doc_nombre from  smn_inventario.smn_documento  where smn_documento_id is not null  and smn_documento_id=smn_inventario.smn_despacho.smn_documento_origen_id  order by smn_documento_id ) as smn_documento_origen_id_combo,
	(select usr_usuario from  smn_base.smn_usuarios  where smn_usuarios_id is not null  and smn_usuarios_id=smn_inventario.smn_despacho.smn_usuario_solicitante_rf  order by smn_usuarios_id ) as smn_usuario_solicitante_rf_combo,
	(select doc_codigo||' - '||doc_nombre from  smn_inventario.smn_documento  where smn_documento_id is not null  and smn_documento_id=smn_inventario.smn_despacho.smn_documento_id  order by smn_documento_id ) as smn_documento_id_combo,
	(select ent_codigo  ||' - '|| ent_descripcion_corta from  smn_base.smn_entidades  where smn_entidades_id is not null  and smn_entidades_id=smn_inventario.smn_despacho.smn_entidad_rf  order by smn_entidades_id ) as smn_entidad_rf_combo,
	(select suc_codigo  ||' - '|| suc_nombre from  smn_base.smn_sucursales  where smn_sucursales_id is not null  and smn_sucursales_id=smn_inventario.smn_despacho.smn_sucursal_rf  order by smn_sucursales_id ) as smn_sucursal_rf_combo,
	(select alm_codigo  ||' - '|| alm_nombre from  smn_base.smn_almacen  where smn_almacen_id is not null  and smn_almacen_id=smn_inventario.smn_despacho.smn_almacen_rf  order by smn_almacen_id ) as smn_almacen_rf_combo,
	(select cla_codigo  ||' - '|| cla_nombre from  smn_base.smn_clase_auxiliar  where smn_clase_auxiliar_id is not null  and smn_clase_auxiliar_id=smn_inventario.smn_despacho.smn_clase_auxiliar_rf  order by smn_clase_auxiliar_id ) as smn_clase_auxiliar_rf_combo,
	(select aux_codigo  ||' - '|| aux_descripcion from  smn_base.smn_auxiliar  where smn_auxiliar_id is not null  and smn_auxiliar_id=smn_inventario.smn_despacho.smn_auxiliar_rf  order by smn_auxiliar_id ) as smn_auxiliar_rf_combo,
	(select eor_codigo  ||' - '|| eor_nombre from  smn_base.smn_estructura_organizacional  where smn_estructura_organizacional_id is not null  and smn_estructura_organizacional_id=smn_inventario.smn_despacho.smn_unidad_organizativa_rf  order by smn_estructura_organizacional_id ) as smn_unidad_organizativa_rf_combo,
	(select cco_codigo  ||' - '|| cco_descripcion_corta from  smn_base.smn_centro_costo  where smn_centro_costo_id is not null  and smn_centro_costo_id=smn_inventario.smn_despacho.smn_centro_costo_rf  order by smn_centro_costo_id ) as smn_centro_costo_rf_combo,
	(select dir_codigo  ||' - '|| dir_descripcion from  smn_base.smn_direccion  where smn_direccion_id is not null  and smn_direccion_id=smn_inventario.smn_despacho.smn_direccion_rf  order by smn_direccion_id ) as smn_direccion_rf_combo,
	(select zon_codigo  ||' - '|| zon_descripcion from  smn_base.smn_zona  where smn_zona_id is not null  and smn_zona_id=smn_inventario.smn_despacho.smn_zona_rf   order by smn_zona_id ) as smn_zona_rf_combo,
	(select rut_codigo  ||' - '|| rut_nombre from  smn_inventario.smn_ruta  where smn_ruta_id is not null  and smn_ruta_id=smn_inventario.smn_despacho.smn_ruta_id  order by smn_ruta_id ) as smn_ruta_id_combo,
	(select tra_codigo  ||' - '|| tra_usuario from  smn_inventario.smn_transporte  where smn_transporte_id is not null  and smn_transporte_id=smn_inventario.smn_despacho.smn_usuario_transportista_rf  order by smn_transporte_id ) as smn_usuario_transportista_rf_combo,
	(select tra_codigo  ||' - '|| tra_descripcion_transporte from  smn_inventario.smn_transporte  where smn_transporte_id is not null  and smn_transporte_id=smn_inventario.smn_despacho.smn_transporte_id  order by smn_transporte_id ) as smn_transporte_id_combo,
	case
		when smn_inventario.smn_despacho.des_estatus='GE' then '${lbl:b_generada}'
		when smn_inventario.smn_despacho.des_estatus='TR' then '${lbl:b_transporte}'
		when smn_inventario.smn_despacho.des_estatus='AP' then '${lbl:b_aprobada}'
		when smn_inventario.smn_despacho.des_estatus='RE' then '${lbl:b_rechazada}'
		when smn_inventario.smn_despacho.des_estatus='EN' then '${lbl:b_entregada}'
		when smn_inventario.smn_despacho.des_estatus='PE' then '${lbl:b_parcialmente_entregada}'
		when smn_inventario.smn_despacho.des_estatus='CE' then '${lbl:b_cerrada}'
	end as des_estatus_combo,
	smn_inventario.smn_despacho.*
from 
	smn_inventario.smn_despacho
where
		smn_inventario.smn_despacho.smn_despacho_id = ${fld:id}
