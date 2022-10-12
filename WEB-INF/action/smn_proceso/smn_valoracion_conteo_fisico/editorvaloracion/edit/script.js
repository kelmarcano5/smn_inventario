setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_conteo_id','${fld:smn_conteo_id}','form1');
setComboValue('smn_documento_id','${fld:smn_documento_id}','form1');
document.form1.vcf_numero_documento.value='${fld:vcf_numero_documento@#,###,###}';
setComboValue('smn_item_id','${fld:smn_item_id}','form1');
document.form1.vcf_cantidad_contada.value='${fld:vcf_cantidad_contada@#,###,##0.00}';
document.form1.vcf_cantidad_existencia.value='${fld:vcf_cantidad_existencia@#,###,##0.00}';
document.form1.vcf_cantidad_diferencia.value='${fld:vcf_cantidad_diferencia@#,###,##0.00}';
setComboValue('smn_unidad_medida_almacenaje_id','${fld:smn_unidad_medida_almacenaje_id}','form1');
document.form1.vcf_costo_ml.value='${fld:vcf_costo_ml@#,###,##0.00}';
document.form1.vcf_costo_ma.value='${fld:vcf_costo_ma@#,###,##0.00}';
document.form1.vcf_monto_ml.value='${fld:vcf_monto_ml@#,###,##0.00}';
setComboValue('smn_tasa_rf','${fld:smn_tasa_rf}','form1');
setComboValue('smn_moneda_rf','${fld:smn_moneda_rf}','form1');
document.form1.vcf_monto_ma.value='${fld:vcf_monto_ma@#,###,##0.00}';
setComboValue('vcf_estatus','${fld:vcf_estatus}','form1');
document.form1.id.value='${fld:smn_valoracion_conteo_fisico_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

