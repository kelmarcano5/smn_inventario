UPDATE smn_inventario.smn_valoracion_conteo_fisico SET
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_conteo_id=${fld:smn_conteo_id},
	smn_documento_id=${fld:smn_documento_id},
	vcf_numero_documento=${fld:vcf_numero_documento},
	smn_item_id=${fld:smn_item_id},
	vcf_cantidad_contada=${fld:vcf_cantidad_contada},
	vcf_cantidad_existencia=${fld:vcf_cantidad_existencia},
	vcf_cantidad_diferencia=${fld:vcf_cantidad_diferencia},
	smn_unidad_medida_almacenaje_id=${fld:smn_unidad_medida_almacenaje_id},
	vcf_costo_ml=${fld:vcf_costo_ml},
	vcf_costo_ma=${fld:vcf_costo_ma},
	vcf_monto_ml=${fld:vcf_monto_ml},
	smn_tasa_rf=${fld:smn_tasa_rf},
	smn_moneda_rf=${fld:smn_moneda_rf},
	vcf_monto_ma=${fld:vcf_monto_ma},
	vcf_estatus=${fld:vcf_estatus},
	vcf_idioma='${def:locale}',
	vcf_usuario='${def:user}',
	vcf_fecha_registro={d '${def:date}'},
	vcf_hora='${def:time}'

WHERE
	smn_valoracion_conteo_fisico_id=${fld:id}

