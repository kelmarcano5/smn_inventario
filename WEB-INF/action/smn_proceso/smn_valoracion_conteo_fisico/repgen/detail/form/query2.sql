select
		smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf,
	smn_inventario.smn_valoracion_conteo_fisico.smn_conteo_id,
	smn_inventario.smn_valoracion_conteo_fisico.smn_documento_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_numero_documento,
	smn_inventario.smn_valoracion_conteo_fisico.smn_item_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_contada,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_existencia,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_cantidad_diferencia,
	smn_inventario.smn_valoracion_conteo_fisico.smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ml,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_costo_ma,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ml,
	smn_inventario.smn_valoracion_conteo_fisico.smn_tasa_rf,
	smn_inventario.smn_valoracion_conteo_fisico.smn_moneda_rf,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_monto_ma,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_estatus,
	smn_inventario.smn_valoracion_conteo_fisico.vcf_fecha_registro
from
	smn_inventario.smn_valoracion_conteo_fisico 
where
	smn_inventario.smn_valoracion_conteo_fisico.smn_valoracion_conteo_fisico_id = ${fld:id}
