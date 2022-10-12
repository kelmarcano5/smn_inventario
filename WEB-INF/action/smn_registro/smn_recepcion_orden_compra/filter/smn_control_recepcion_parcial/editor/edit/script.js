document.form1.smn_movimiento_cabecera_id_name.value='${fld:mca_estatus_proceso_pl0@js}';
document.form1.smn_movimiento_cabecera_id.value='${fld:smn_movimiento_cabecera_id@#,###,###}';
document.form1.smn_orden_compra_rf.value='${fld:smn_orden_compra_rf@#,###,###}';
document.form1.crp_numero_documento.value='${fld:crp_numero_documento@#,###,###}';
setComboValue('smn_item_id','${fld:smn_item_id}','form1');
document.form1.crp_cantidad_recibida.value='${fld:crp_cantidad_recibida@#,###,###}';
document.form1.crp_fecha_recepcion.value='${fld:crp_fecha_recepcion@dd-MM-yyyy}';
document.form1.crp_lote.value='${fld:crp_lote@#,###,###}';
document.form1.crp_fecha_vencimiento_lote.value='${fld:crp_fecha_vencimiento_lote@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_control_recepcion_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

