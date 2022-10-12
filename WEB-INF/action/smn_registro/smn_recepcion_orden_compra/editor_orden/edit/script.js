setComboValue('smn_entidad_rf','${fld:smn_entidad_rf}','form1');
setComboValue('smn_sucursal_rf','${fld:smn_sucursal_rf}','form1');
setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_documento_id','${fld:smn_documento_id}','form1');
chgCombo3(null,'${fld:smn_entidad_rf}',null,'${fld:smn_sucursal_rf}',null,'${fld:smn_almacen_rf}');

if('${fld:mca_recibo}'=='0')
	document.form1.mca_recibo.value='';
else
	document.form1.mca_recibo.value='${fld:mca_recibo}';

document.form1.tipo_documento_pago_id.value='NE';
setComboValue('smn_proveedor_rf','${fld:smn_proveedor_rf}','form1');
document.form1.smn_orden_compra_rf.value='${fld:smn_orden_compra_rf}';
document.form1.smn_orden_compra_numero.value='${fld:occ_orden_compra_numero}';
document.form1.id.value='${fld:smn_movimiento_cabecera_id}';

document.form1.mca_fecha_recibida.disabled=true;
document.form1.mca_fecha_recibida.value='${fld:mca_fecha_recibida}';

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


 

