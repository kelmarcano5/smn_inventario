var conteo = '${fld:smn_conteo_id}';
$('#smn_conteo_id').val(conteo).trigger('change');

var item = '${fld:smn_item_id}';
$('#smn_item_id').val(item).trigger('change');

var unidad = '${fld:smn_unidad_medida_almacenaje_id}';
$('#smn_unidad_medida_almacenaje_id').val(unidad).trigger('change');

document.form1.cfi_fecha_vencimiento.value='${fld:cfi_fecha_vencimiento@dd-MM-yyyy}';
document.form1.cfi_numero_control_lote.value='${fld:cfi_numero_control_lote@js}';

setComboValue('smn_conteo_id','${fld:smn_conteo_id}','form1');
//document.form1.smn_conteo_id.value='${fld:smn_conteo_id@#,###,###}';
setComboValue('smn_item_id','${fld:smn_item_id}','form1');
document.form1.cfi_cantidad.value='${fld:cfi_cantidad@#,###,###}';
setComboValue('smn_unidad_medida_almacenaje_id','${fld:smn_unidad_medida_almacenaje_id}','form1');
document.form1.cfi_costo.value='${fld:cfi_costo@#,###,##0.00}';
setComboValue('smn_auxiliar_1_rf','${fld:smn_auxiliar_1_rf}','form1');
setComboValue('smn_auxiliar_2_rf','${fld:smn_auxiliar_2_rf}','form1');
setComboValue('cfi_estatus','${fld:cfi_estatus}','form1');
document.form1.id.value='${fld:smn_conteo_inventario_fisico_id@###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 


