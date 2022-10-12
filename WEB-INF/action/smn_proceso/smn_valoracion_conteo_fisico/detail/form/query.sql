select
	smn_inventario.smn_valoracion_conteo_fisico.smn_valoracion_conteo_fisico_id,
	smn_base.smn_almacen.alm_codigo ||'-'|| smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_conteo.con_codigo ||''|| smn_inventario.smn_conteo.con_descripcion as smn_conteo_id,
	smn_inventario.smn_documento.doc_codigo ||' - '|| smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_numero_documento,
	smn_base.smn_item.itm_codigo||'-'|| smn_base.smn_item.itm_nombre as smn_item_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_contada,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_existencia,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_diferencia,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_numero_documento,
	smn_inventario.smn_valoracion_conteo_fisico.smn_moneda_rf,
	smn_inventario.smn_valoracion_conteo_fisico.smn_tasa_rf,
	smn_base.smn_unidad_medida.unm_codigo ||' - '|| smn_base.smn_unidad_medida.unm_descripcion as smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ml,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ml,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ma,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ma,	
		case
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus='VL' then '${lbl:b_valoration}'
	end as vcf_estatus,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_registro
	
from
	smn_inventario.smn_valoracion_conteo_fisico
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf
	inner join smn_inventario.smn_conteo on smn_inventario.smn_conteo.smn_conteo_id = smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id
	inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_valoracion_conteo_fisico.smn_item_id
	left outer join smn_base.smn_monedas on smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_valoracion_conteo_fisico.smn_moneda_rf
	left outer join smn_base.smn_tasas_de_cambio on smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_valoracion_conteo_fisico.smn_tasa_rf
	inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_unidad_medida_almacenamiento_rf = smn_inventario.smn_valoracion_conteo_fisico.smn_unidad_medida_almacenaje_id
	inner join smn_base.smn_unidad_medida on smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_valoracion_conteo_fisico.smn_unidad_medida_almacenaje_id
where
	smn_valoracion_conteo_fisico_id = ${fld:id}
