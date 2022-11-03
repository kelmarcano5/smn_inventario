select
	smn_inventario.smn_valoracion_conteo_fisico.smn_valoracion_conteo_fisico_id,
	smn_base.smn_almacen.alm_codigo ||' - '|| smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_conteo.con_codigo ||' - '|| smn_inventario.smn_conteo.con_descripcion as smn_conteo_id,
	smn_inventario.smn_documento.doc_codigo ||' - '|| smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_numero_documento,
	smn_base.smn_item.itm_codigo||' - '|| smn_base.smn_item.itm_nombre as smn_item_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_contada,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_registro,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_generacion,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ml,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ml,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ma,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ma,
	case
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='VL' then '${lbl:b_valoration}'
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='CE' then 'Cerrado'
	end as vcf_estatus

from
	smn_inventario.smn_valoracion_conteo_fisico
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf
	inner join smn_inventario.smn_conteo on smn_inventario.smn_conteo.smn_conteo_id = smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id
	LEFT join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_valoracion_conteo_fisico.smn_item_id
	inner join smn_seguridad.s_user on smn_seguridad.s_user.userlogin= 'admin'
	inner join smn_base.smn_usuarios on smn_base.smn_usuarios.smn_user_rf = smn_seguridad.s_user.user_id
	inner join smn_inventario.smn_rol on smn_inventario.smn_rol.smn_usuarios_rf = smn_base.smn_usuarios.smn_usuarios_id
where 
	smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus!='CE'