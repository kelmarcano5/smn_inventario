select DISTINCT
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='CO' then '${lbl:b_posted}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RC' then '${lbl:b_received}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PR' then '${lbl:b_received_partial}'
	end as mca_estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as mca_estatus_financiero,
	CASE 
		WHEN smn_inventario.smn_movimiento_cabecera.mca_recibo = 0 THEN NULL
		WHEN smn_inventario.smn_movimiento_cabecera.mca_recibo > 0 THEN mca_recibo
	END AS mca_recibo,
	smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as smn_modulo_rf,
	smn_base.smn_documentos_generales.dcg_codigo||'-'||smn_base.smn_documentos_generales.dcg_descripcion as smn_documento_origen_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen,
	smn_inventario.smn_documento.doc_codigo ||' - '|| smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro,
	smn_inventario.smn_movimiento_cabecera.smn_tipo_documento_pago_id,
	smn_inventario.smn_documento.doc_tipo_documento_pago,
	smn_base.smn_auxiliar.aux_codigo||'-'||smn_base.smn_auxiliar.aux_descripcion AS smn_proveedor_rf,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_recibida
from
	smn_inventario.smn_movimiento_cabecera
	INNER JOIN smn_base.smn_entidades ON smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	left outer JOIN smn_base.smn_sucursales ON smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf
	LEFT OUTER JOIN  smn_base.smn_almacen ON smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	LEFT OUTER JOIN smn_base.smn_modulos ON smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
	left outer join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
	LEFT OUTER JOIN smn_base.smn_documentos_generales ON smn_base.smn_documentos_generales.smn_documentos_generales_id =  smn_inventario.smn_documento.smn_documento_general_rf
	left outer join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
	LEFT OUTER JOIN smn_seguridad.s_user on smn_seguridad.s_user.userlogin ='${def:user}'
	LEFT OUTER JOIN smn_base.smn_usuarios on smn_base.smn_usuarios.smn_user_rf=smn_seguridad.s_user.user_id
	LEFT OUTER JOIN smn_inventario.smn_rol ON smn_inventario.smn_rol.smn_usuarios_rf = smn_base.smn_usuarios.smn_usuarios_id
	LEFT OUTER JOIN smn_compras.smn_proveedor ON smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf = smn_compras.smn_proveedor.smn_proveedor_id
	LEFT OUTER JOIN smn_base.smn_auxiliar ON smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf 
where
	smn_inventario.smn_tipo_documento.tdc_naturaleza='EN' AND smn_inventario.smn_documento.doc_tipo_documento_pago='NE'
	AND smn_base.smn_modulos.mod_codigo='SMN_COM' 
	AND smn_inventario.smn_rol.rol_tipo = 'AL' 
	AND smn_inventario.smn_rol.rol_estatus = 'AC'
	AND smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso != 'RC'
ORDER BY smn_inventario.smn_movimiento_cabecera.mca_fecha_registro DESC
	