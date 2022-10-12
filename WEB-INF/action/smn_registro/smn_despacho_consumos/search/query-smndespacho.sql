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
	when smn_inventario.smn_despacho.des_estatus='PD' then '${lbl:b_partially_delivered}'
	end as des_estatus_combo,
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
	smn_inventario.smn_despacho.des_fecha_solicitud,
	smn_inventario.smn_despacho.des_fecha_despacho,
	smn_inventario.smn_despacho.des_estatus,
	smn_inventario.smn_despacho.des_fecha_registro,
	smn_base.smn_almacen.alm_codigo ||' - '|| smn_base.smn_almacen.alm_nombre as smn_almacen_rf
from
	smn_inventario.smn_despacho
	left outer join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id=smn_inventario.smn_despacho.smn_modulo_rf
	left outer join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id=smn_inventario.smn_despacho.smn_documento_id
	inner join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id=smn_inventario.smn_despacho.smn_entidad_rf
	left outer join smn_base.smn_clase_auxiliar on smn_base.smn_clase_auxiliar.smn_clase_auxiliar_id=smn_inventario.smn_despacho.smn_clase_auxiliar_rf
	left outer join smn_base.smn_auxiliar on smn_base.smn_auxiliar.smn_auxiliar_id=smn_inventario.smn_despacho.smn_auxiliar_rf
	left outer join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id=smn_inventario.smn_despacho.smn_unidad_organizativa_rf
	left outer join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_despacho.smn_almacen_rf
where smn_base.smn_modulos.mod_codigo='SMN_COM'
ORDER BY smn_inventario.smn_despacho.des_fecha_registro DESC





