$('#smn_item_rf').val('${fld:smn_item_rf}').trigger('change');
document.form1.cit_codigo_barra.value='${fld:cit_codigo_barra@js}';
document.form1.cit_codigo_qr.value='${fld:cit_codigo_qr@js}';
document.form1.cit_codigo_alterno.value='${fld:cit_codigo_alterno@js}';
document.form1.cit_descripcion_tecnica.value='${fld:cit_descripcion_tecnica@js}';
setComboValue('smn_unidad_medida_base_rf','${fld:smn_unidad_medida_base_rf}','form1');
setComboValue('smn_unidad_medida_compra_rf','${fld:smn_unidad_medida_compra_rf}','form1');
setComboValue('smn_unidad_medida_venta_rf','${fld:smn_unidad_medida_venta_rf}','form1');
setComboValue('smn_unidad_medida_almacenamiento_rf','${fld:smn_unidad_medida_almacenamiento_rf}','form1');
setComboValue('smn_unidad_medida_despacho_rf','${fld:smn_unidad_medida_despacho_rf}','form1');
document.form1.cit_peso.value='${fld:cit_peso@#,###,##0.00}';
setComboValue('smn_unidad_medida_peso_rf','${fld:smn_unidad_medida_peso_rf}','form1');
document.form1.cit_alto.value='${fld:cit_alto@#,###,##0.00}';
setComboValue('smn_unidad_medida_alto_rf','${fld:smn_unidad_medida_alto_rf}','form1');
document.form1.cit_ancho.value='${fld:cit_ancho@#,###,##0.00}';
setComboValue('smn_unidad_medida_ancho_rf','${fld:smn_unidad_medida_ancho_rf}','form1');
document.form1.cit_profundidad.value='${fld:cit_profundidad@#,###,##0.00}';
setComboValue('smn_unidad_medida_profundidad_rf','${fld:smn_unidad_medida_profundidad_rf}','form1');
setComboValue('cit_es_medicamento','${fld:cit_es_medicamento}','form1');
setComboValue('smn_principio_activo_rf','${fld:smn_principio_activo_rf}','form1');
//document.form1.smn_principio_activo_rf.value='${fld:smn_principio_activo_rf@js}';
setComboValue('cit_req_control_lote','${fld:cit_req_control_lote}','form1');
setComboValue('cit_req_control_vencimiento','${fld:cit_req_control_vencimiento}','form1');
document.form1.cit_dias_ant_vencimiento.value='${fld:cit_dias_ant_vencimiento@#,###,###}';
setComboValue('cit_tipo_costo','${fld:cit_tipo_costo}','form1');
setComboValue('cit_valoracion_inventario','${fld:cit_valoracion_inventario}','form1');
setComboValue('cit_metodo_despacho','${fld:cit_metodo_despacho}','form1');
// document.form1.cit_cant_minima.value='${fld:cit_cant_minima@#,###,##0.00}';
// document.form1.cit_cant_maxima.value='${fld:cit_cant_maxima@#,###,##0.00}';
// document.form1.cit_punto_reorden.value='${fld:cit_punto_reorden@#,###,##0.00}';
// document.form1.cit_cant_seguridad.value='${fld:cit_cant_seguridad@#,###,##0.00}';
setComboValue('cit_es_reusable','${fld:cit_es_reusable}','form1');
setComboValue('cit_reuso_apertura','${fld:cit_reuso_apertura}','form1');
document.form1.cit_cant_reuso.value='${fld:cit_cant_reuso@#,###,##0.00}';
setComboValue('cit_origen_producto','${fld:cit_origen_producto}','form1');
document.form1.cit_descripcion_compra.value='${fld:cit_descripcion_compra@js}';
document.form1.cit_codigo_arancel.value='${fld:cit_codigo_arancel@js}';
document.form1.cit_dias_entrega.value='${fld:cit_dias_entrega@#,###,##0.00}';
setComboValue('smn_centro_costo_rf','${fld:smn_centro_costo_rf}','form1');
setComboValue('cit_proveedor_exclusivo','${fld:cit_proveedor_exclusivo}','form1');
setComboValue('cit_almacenado','${fld:cit_almacenado}','form1');
setComboValue('cit_estatus','${fld:cit_estatus}','form1');
setComboValue('cit_tipo_producto','${fld:cit_tipo_producto}','form1');
setComboValue('smn_clasificacion_abc','${fld:smn_clasificacion_abc}','form1');
setComboValue('cit_item_compuesto','${fld:cit_item_compuesto}','form1');
document.form1.cit_referencia.value='${fld:cit_referencia@#######}';
document.form1.cit_vigencia.value='${fld:cit_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_caracteristica_item_id@#,###,###}';

document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");

var tp = '${fld:cit_tipo_producto}';
	//alert(tp);
if(tp == 'FA'){

	document.getElementById("metdesp").style.display="";
	document.getElementById("reuape").style.display="";
	document.getElementById("cantreu").style.display="";
	document.getElementById("prinact").style.display="";
	document.getElementById("medica").style.display="";
    document.getElementById("ctrlvenc").style.display="";
	document.getElementById("diasvenc").style.display="";
	document.getElementById("undalm").style.display="";
    document.getElementById("unddesp").style.display="";
	document.getElementById("reqlote").style.display="";
    document.getElementById("valinv").style.display="";
    document.getElementById("reusa").style.display="";
    document.getElementById("oripro").style.display="";
    document.getElementById("cencos").style.display="";
    document.getElementById("alma").style.display="";
    // document.getElementById("stat").style.display="";
    // document.getElementById("fvig").style.display="";
    document.getElementById("itcomp").style.display="";
    document.getElementById("clasabc").style.display="";
//  alert("fabricado");
}

if(tp == 'CO'){
	document.getElementById("proexc").style.display="";
	document.getElementById("undcompra").style.display="";
	document.getElementById("arancel").style.display="";
	document.getElementById("tipcosto").style.display="";
	document.getElementById("diaentr").style.display="";
  	document.getElementById("metdesp").style.display="";
	document.getElementById("reuape").style.display="";
	document.getElementById("cantreu").style.display="";
	document.getElementById("prinact").style.display="";
	document.getElementById("medica").style.display="";
    document.getElementById("ctrlvenc").style.display="";
	document.getElementById("diasvenc").style.display="";
	document.getElementById("undalm").style.display="";
    document.getElementById("unddesp").style.display="";
	document.getElementById("reqlote").style.display="";
    document.getElementById("valinv").style.display="";
    document.getElementById("reusa").style.display="";
    document.getElementById("oripro").style.display="";
    document.getElementById("cencos").style.display="";
    document.getElementById("alma").style.display="";
    // document.getElementById("stat").style.display="";
    // document.getElementById("fvig").style.display="";
    document.getElementById("itcomp").style.display="";
    document.getElementById("clasabc").style.display="";

}

if(tp == 'PR'){
    //alert(tp);
    document.getElementById("proexc").style.display="none";
    document.getElementById("undcompra").style.display="none";
    document.getElementById("arancel").style.display="none";
    document.getElementById("tipcosto").style.display="none";
    document.getElementById("diaentr").style.display="none";
    document.getElementById("metdesp").style.display="none";
    document.getElementById("reuape").style.display="none";
    document.getElementById("cantreu").style.display="none";
    document.getElementById("prinact").style.display="none";
    document.getElementById("medica").style.display="none";
    document.getElementById("ctrlvenc").style.display="none";
    document.getElementById("diasvenc").style.display="none";
    document.getElementById("undalm").style.display="none";
    document.getElementById("unddesp").style.display="none";

    document.getElementById("reqlote").style.display="";
    document.getElementById("valinv").style.display="";
    document.getElementById("reusa").style.display="";
    document.getElementById("oripro").style.display="";
    document.getElementById("cencos").style.display="";
    document.getElementById("alma").style.display="";
    document.getElementById("itcomp").style.display="";
    document.getElementById("clasabc").style.display="";
  //alert("promocion");
 }

// <rows>selectSmnAlmacenId('${fld:smn_almacen_id}','${fld:alm_codigo}');</rows> 
// <rows>selectSmnCodImpuestoId('${fld:smn_codigos_impuestos_id}','${fld:imp_codigo}');</rows> 


 

