select
	smn_inventario.smn_despacho.smn_despacho_id,
	case
	when smn_inventario.smn_despacho.des_estatus='GE' then '${lbl:b_generada}'
	when smn_inventario.smn_despacho.des_estatus='TR' then '${lbl:b_transporte}'
	when smn_inventario.smn_despacho.des_estatus='AP' then '${lbl:b_aprobada}'
	when smn_inventario.smn_despacho.des_estatus='RE' then '${lbl:b_rechazada}'
	when smn_inventario.smn_despacho.des_estatus='EN' then '${lbl:b_entregada}'
	when smn_inventario.smn_despacho.des_estatus='PE' then '${lbl:b_parcialmente_entregada}'
	when smn_inventario.smn_despacho.des_estatus='CE' then '${lbl:b_cerrada}'
	end as des_estatus_combo,
	smn_base.smn_modulos.mod_codigo ||' - '||mod_nombre as smn_modulo_rf, 
	smn_inventario.smn_documento.doc_codigo||' - '||doc_nombre as smn_documento_origen_id,
	smn_inventario.smn_despacho.des_numero_documento_origen,
	smn_base.smn_usuarios.usr_usuario as smn_usuario_solicitante_rf,
	smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_despacho.des_numero_documento,
	smn_inventario.smn_despacho.des_descripcion,
	smn_base.smn_entidades.ent_codigo ||' - '||ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_sucursales.suc_codigo ||' - '|| suc_nombre as smn_sucursal_rf,
	smn_base.smn_almacen.alm_codigo ||' - '|| alm_nombre as smn_almacen_rf,
	smn_base.smn_clase_auxiliar.cla_codigo ||' - '||cla_nombre as smn_clase_auxiliar_rf,
	smn_base.smn_auxiliar.aux_codigo ||' - '||aux_descripcion as smn_auxiliar_rf,
	smn_base.smn_estructura_organizacional.eor_codigo ||' - '|| eor_nombre as smn_unidad_organizativa_rf,
	smn_base.smn_centro_costo.cco_codigo ||' - '|| cco_descripcion_corta as smn_centro_costo_rf, 
	smn_base.smn_direccion.dir_codigo ||' - '|| dir_descripcion as smn_direccion_rf,
	smn_base.smn_zona.zon_codigo ||' - '|| zon_descripcion as smn_zona_rf,
	smn_inventario.smn_ruta.rut_codigo ||' - '||rut_nombre as smn_ruta_id,
	smn_inventario.smn_transporte.tra_codigo ||' - '|| tra_usuario as smn_usuario_transportista_rf,
	smn_inventario.smn_transporte.tra_codigo ||' - '|| tra_descripcion_transporte as smn_transporte_id,
	smn_inventario.smn_despacho.des_chofer,
	smn_inventario.smn_despacho.des_fecha_solicitud,
	smn_inventario.smn_despacho.des_fecha_despacho,
	smn_inventario.smn_despacho.des_fecha_registro
	
from
	smn_inventario.smn_despacho
	left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id=smn_inventario.smn_despacho.smn_modulo_rf
	left join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id=smn_inventario.smn_despacho.smn_documento_origen_id
	left join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id=smn_inventario.smn_despacho.smn_entidad_rf
	left join smn_base.smn_clase_auxiliar on smn_base.smn_clase_auxiliar.smn_clase_auxiliar_id=smn_inventario.smn_despacho.smn_clase_auxiliar_rf
	left join smn_base.smn_auxiliar on smn_base.smn_auxiliar.smn_auxiliar_id=smn_inventario.smn_despacho.smn_auxiliar_rf
	left join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id=smn_inventario.smn_despacho.smn_unidad_organizativa_rf
	left join smn_base.smn_usuarios on smn_base.smn_usuarios.smn_usuarios_id=smn_inventario.smn_despacho.smn_usuario_solicitante_rf
	left join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id=smn_inventario.smn_despacho.smn_sucursal_rf
	left join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_despacho.smn_almacen_rf
	left join smn_base.smn_centro_costo on smn_base.smn_centro_costo.smn_centro_costo_id=smn_inventario.smn_despacho.smn_centro_costo_rf
	left join smn_base.smn_direccion on smn_base.smn_direccion.smn_direccion_id=smn_inventario.smn_despacho.smn_direccion_rf
	left join smn_base.smn_zona on smn_base.smn_zona.smn_zona_id=smn_inventario.smn_despacho.smn_zona_rf
	left join smn_inventario.smn_ruta on smn_inventario.smn_ruta.smn_ruta_id=smn_inventario.smn_despacho.smn_ruta_id
	left join smn_inventario.smn_transporte on smn_inventario.smn_transporte.smn_transporte_id=smn_inventario.smn_despacho.smn_usuario_transportista_rf

where
	smn_inventario.smn_despacho.smn_despacho_id = ${fld:id}
