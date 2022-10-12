setComboValue('smn_tipo_documento_id','${fld:smn_tipo_documento_id}','form1');
document.form1.doc_codigo.value='${fld:doc_codigo}';
document.form1.doc_nombre.value='${fld:doc_nombre}';
setComboValue('smn_documento_general_rf','${fld:smn_documento_general_rf}','form1');
setComboValue('smn_modulo_origen_rf','${fld:smn_modulo_origen_rf}','form1');
setComboValue('doc_despacho_int_consumo','${fld:doc_despacho_int_consumo}','form1');
setComboValue('doc_despacho_int_transferencia','${fld:doc_despacho_int_transferencia}','form1');
setComboValue('doc_despacho_venta','${fld:doc_despacho_venta}','form1');
//document.form1.doc_secuencia.value='${fld:doc_secuencia}';
document.form1.doc_vigencia.value='${fld:doc_vigencia}';
setComboValue('doc_estatus','${fld:doc_estatus}','form1');
setComboValue('doc_tipo_movimiento','${fld:doc_tipo_movimiento}','form1');
setComboValue('doc_tipo_documento_pago','${fld:doc_tipo_documento_pago}','form1');
document.form1.id.value='${fld:smn_documento_id}';

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");

document.form1.doc_codigo.disabled=true;
 

