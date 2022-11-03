SELECT
	smn_inventario.smn_valoracion_conteo_fisico.smn_item_id,
    smn_inventario.smn_documento.doc_nombre as descripcion,
	case when smn_inventario.smn_caracteristica_item.smn_centro_costo_rf is null then 0 else smn_inventario.smn_caracteristica_item.smn_centro_costo_rf end smn_centro_costo_rf,
	smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_diferencia,
	smn_inventario.smn_valoracion_conteo_fisico.smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_tipo_documento.tdc_naturaleza as tipo_movimiento,
	case when smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ml is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ml end as vcf_monto_ml, 
	case when smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ma is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ma end as vcf_monto_ma,
	case when smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ml is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ml end as vcf_costo_ml,
	case when smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ma is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ma end vcf_costo_ma,
	case when smn_inventario.smn_valoracion_conteo_fisico.smn_tasa_rf is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.smn_tasa_rf end as smn_tasa_rf,
	case when smn_inventario.smn_valoracion_conteo_fisico.smn_moneda_rf is null then 0 else smn_inventario.smn_valoracion_conteo_fisico.smn_moneda_rf end as smn_moneda_rf,
	case when smn_inventario.smn_valoracion_conteo_fisico.vcf_lote is null then '' else smn_inventario.smn_valoracion_conteo_fisico.vcf_lote end as vcf_lote,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_vencimiento_lote,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_contada
FROM
	smn_inventario.smn_valoracion_conteo_fisico
	left join smn_inventario.smn_caracteristica_item ON smn_inventario.smn_caracteristica_item.smn_item_rf = smn_inventario.smn_valoracion_conteo_fisico.smn_item_id 
	inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id
	inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
WHERE
	smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id = ${fld:conteo} 
	AND smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus = 'AP';
