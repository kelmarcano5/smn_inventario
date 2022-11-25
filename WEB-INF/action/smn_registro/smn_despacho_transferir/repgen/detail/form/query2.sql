select
	smn_inventario.smn_despacho.smn_despacho_id,
	smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id,
	case
	when smn_inventario.smn_despacho.des_estatus='GE' then '${lbl:b_generada}'
	when smn_inventario.smn_despacho.des_estatus='TR' then '${lbl:b_transporte}'
	when smn_inventario.smn_despacho.des_estatus='AP' then '${lbl:b_aprobada}'
	when smn_inventario.smn_despacho.des_estatus='RE' then '${lbl:b_rechazada}'
	when smn_inventario.smn_despacho.des_estatus='EN' then '${lbl:b_entregada}'
	when smn_inventario.smn_despacho.des_estatus='PE' then '${lbl:b_parcialmente_entregada}'
	when smn_inventario.smn_despacho.des_estatus='CE' then '${lbl:b_cerrada}'
	end as des_estatus,
	smn_base.smn_modulos.mod_codigo ||' - '||mod_nombre as smn_modulo_rf, 
	des_descripcion as cfc_nombre,
	smn_inventario.smn_documento.doc_codigo||' - '||doc_nombre as smn_documento_origen_id,
	smn_inventario.smn_despacho.des_numero_documento_origen,
	smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_despacho.des_numero_documento,
	smn_inventario.smn_despacho.des_descripcion,
	smn_base.smn_entidades.ent_codigo ||' - '||ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_clase_auxiliar.cla_codigo ||' - '||cla_nombre as smn_clase_auxiliar_rf,
	smn_base.smn_auxiliar.aux_codigo ||' - '||aux_descripcion as smn_auxiliar_rf,
	smn_base.smn_estructura_organizacional.eor_codigo ||' - '|| eor_nombre as smn_unidad_organizativa_rf,
	smn_inventario.smn_despacho.des_fecha_despacho,
	smn_inventario.smn_despacho.des_estatus,
	smn_inventario.smn_despacho.des_fecha_registro,
	smn_base.smn_sucursales.suc_codigo||' - '||suc_nombre as smn_sucursal_rf,
	smn_base.smn_monedas.mon_codigo||' - '||smn_base.smn_monedas.mon_nombre as smn_moneda_rf,
	smn_base.smn_tasas_de_cambio.tca_moneda_referencia||' - '||tca_tasa_cambio as smn_tasa_rf,
	smn_base.smn_almacen.alm_codigo||' - '||alm_nombre as smn_almacen_rf,
	smn_base.smn_centro_costo.cco_codigo||' - '||cco_descripcion_corta as smn_centro_costo_rf,
	smn_base.smn_direccion.dir_descripcion||' - '||dir_codigo as smn_direccion_rf,
	smn_base.smn_zona.zon_descripcion||' - '||zon_codigo as smn_zona_rf,
	smn_inventario.smn_ruta.rut_nombre||' - '||rut_codigo as smn_ruta_id,
	smn_inventario.smn_transporte.tra_codigo||' - '||tra_descripcion_transporte as smn_transporte_id,
	s.usr_nombres||' - '||s.usr_apellidos as smn_usuario_solicitante_rf,
	t.usr_nombres||' - '||t.usr_apellidos as smn_usuario_transportista_rf,
	case
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='DE' then '${lbl:b_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='RE' then '${lbl:b_rejected}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='PD' then '${lbl:b_partially_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='CE' then '${lbl:b_closed}'
	end as dde_estatus_transaccion,
	smn_base.smn_item.itm_codigo ||' - '||itm_nombre as smn_caracteristica_item_id,
	dde_cantidad_solicitada,
	dde_cantidad_despachada,
	dde_costo_ml,
	dde_costo_ma,
	dde_motivo,
	smn_base.smn_v_usuarios.usr_nombres||' - '||smn_v_usuarios.usr_apellidos as smn_usuario_aprobador_rf,
	dde_fecha_aprobacion,
	dde_fecha_cierre,
	smn_inventario.smn_despacho.des_chofer,
 	smn_inventario.smn_despacho.des_fecha_solicitud,
 	smn_inventario.smn_despacho.des_fecha_despacho,
 	smn_inventario.smn_despacho.des_monto_pedido_ml,
 	smn_inventario.smn_despacho.des_monto_impuesto_ml,
 	smn_inventario.smn_despacho.des_monto_descuento_ml,
 	smn_inventario.smn_despacho.des_monto_bonificacion_ml,
 	smn_inventario.smn_despacho.des_monto_neto_ml,
 	smn_inventario.smn_despacho.des_monto_pedido_ma,
 	smn_inventario.smn_despacho.des_monto_impuesto_ma,
 	smn_inventario.smn_despacho.des_monto_descuento_ma,
 	smn_inventario.smn_despacho.des_monto_bonificacion_ma,
 	smn_inventario.smn_despacho.des_monto_neto_ma,
	smn_inventario.smn_despacho.des_idioma,
	smn_inventario.smn_despacho.des_usuario,
	smn_inventario.smn_despacho.des_hora,
 	smn_inventario.smn_despacho.des_fecha_registro,
	smn_inventario.smn_despacho_detalle.dde_idioma,
	smn_inventario.smn_despacho_detalle.dde_usuario,
	smn_inventario.smn_despacho_detalle.dde_hora,
	smn_inventario.smn_despacho_detalle.dde_fecha_registro
from
	smn_inventario.smn_despacho
	left outer join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id=smn_inventario.smn_despacho.smn_modulo_rf
	left outer join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id=smn_inventario.smn_despacho.smn_documento_id
	left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id=smn_inventario.smn_despacho.smn_entidad_rf
	left outer join smn_base.smn_clase_auxiliar on smn_base.smn_clase_auxiliar.smn_clase_auxiliar_id=smn_inventario.smn_despacho.smn_clase_auxiliar_rf
	left outer join smn_base.smn_auxiliar on smn_base.smn_auxiliar.smn_auxiliar_id=smn_inventario.smn_despacho.smn_auxiliar_rf
	left outer join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id=smn_inventario.smn_despacho.smn_unidad_organizativa_rf
	left outer join smn_inventario.smn_despacho_detalle on smn_inventario.smn_despacho_detalle.smn_despacho_id = smn_inventario.smn_despacho.smn_despacho_id
	left outer join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id = smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id
	left outer join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_caracteristica_item.smn_item_rf
	left outer join smn_base.smn_v_usuarios on smn_base.smn_v_usuarios.smn_usuarios_id = smn_inventario.smn_despacho_detalle.smn_usuario_aprobador_rf
	left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_despacho.smn_sucursal_rf
	left outer join smn_base.smn_monedas on smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_despacho.smn_moneda_rf
 	left outer join smn_base.smn_tasas_de_cambio on smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_despacho.smn_tasa_rf
	left outer join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_despacho.smn_almacen_rf
	left outer join smn_base.smn_centro_costo on smn_base.smn_centro_costo.smn_centro_costo_id = smn_inventario.smn_despacho.smn_centro_costo_rf
	left outer join smn_base.smn_direccion on smn_base.smn_direccion.smn_direccion_id = smn_inventario.smn_despacho.smn_direccion_rf
	left outer join smn_base.smn_zona on smn_base.smn_zona.smn_zona_id = smn_inventario.smn_despacho.smn_zona_rf
	left outer join smn_inventario.smn_ruta on smn_inventario.smn_ruta.smn_ruta_id = smn_inventario.smn_despacho.smn_ruta_id
	left outer join smn_inventario.smn_transporte on smn_inventario.smn_transporte.smn_transporte_id = smn_inventario.smn_despacho.smn_transporte_id
	left outer join smn_base.smn_v_usuarios s on smn_base.smn_v_usuarios.smn_usuarios_id = smn_inventario.smn_despacho.smn_usuario_solicitante_rf
	left outer join smn_base.smn_v_usuarios t on smn_base.smn_v_usuarios.smn_usuarios_id = smn_inventario.smn_despacho.smn_usuario_transportista_rf
where
	smn_inventario.smn_despacho.smn_despacho_id = ${fld:id}