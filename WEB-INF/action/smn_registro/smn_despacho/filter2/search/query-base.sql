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
	smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_base.smn_auxiliar.aux_codigo ||' - '||aux_descripcion as smn_auxiliar_rf,
	smn_base.smn_estructura_organizacional.eor_codigo ||' - '|| eor_nombre as smn_unidad_organizativa_rf,
	smn_inventario.smn_despacho.des_fecha_despacho,
	smn_inventario.smn_despacho.des_estatus,
	smn_inventario.smn_despacho.des_fecha_registro,
	smn_inventario.smn_despacho.des_descripcion,
	smn_base.smn_direccion.dir_codigo ||' - '|| smn_base.smn_direccion.dir_descripcion as smn_direccion_rf_combo,
	smn_inventario.smn_despacho.des_numero_documento_origen as numero_documento,
	smn_base.smn_zona.zon_descripcion as smn_zona_rf,
	smn_inventario.smn_transporte.tra_descripcion_transporte as chofer,
	smn_base.smn_item.itm_nombre as smn_item_rf,
	smn_inventario.smn_despacho_detalle.dde_cantidad_solicitada
from
	smn_inventario.smn_despacho
	inner join smn_inventario.smn_despacho_detalle on smn_inventario.smn_despacho_detalle.smn_despacho_id = 	smn_inventario.smn_despacho.smn_despacho_id
	left join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id=smn_inventario.smn_despacho.smn_documento_id
	left join smn_base.smn_auxiliar on smn_base.smn_auxiliar.smn_auxiliar_id=smn_inventario.smn_despacho.smn_auxiliar_rf
	left join smn_base.smn_estructura_organizacional on smn_base.smn_estructura_organizacional.smn_estructura_organizacional_id=smn_inventario.smn_despacho.smn_unidad_organizativa_rf
	left join smn_base.smn_direccion on smn_base.smn_direccion.smn_direccion_id = smn_inventario.smn_despacho.smn_direccion_rf
	left join smn_base.smn_zona on smn_base.smn_zona.smn_zona_id = smn_inventario.smn_despacho.smn_zona_rf
	left join smn_base.smn_rel_auxiliar_direccion on smn_base.smn_rel_auxiliar_direccion.smn_zona_rf = smn_inventario.smn_despacho.smn_zona_rf
	left join smn_inventario.smn_transporte on smn_inventario.smn_transporte.smn_transporte_id = cast(smn_inventario.smn_despacho.des_chofer as INTEGER)
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id
where
	smn_inventario.smn_despacho.smn_despacho_id is not null
	${filter}
ORDER BY smn_inventario.smn_despacho.des_fecha_registro DESC
