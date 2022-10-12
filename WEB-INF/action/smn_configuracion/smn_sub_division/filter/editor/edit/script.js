setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_division_id','${fld:smn_division_id}','form1');
document.form1.sdi_codigo.value='${fld:sdi_codigo@js}';
document.form1.sdi_descripcion.value='${fld:sdi_descripcion@js}';
setComboValue('sdi_es_ubicacion','${fld:sdi_es_ubicacion}','form1');
document.form1.sdi_alto.value='${fld:sdi_alto@#,###,##0.00}';
setComboValue('smn_unidad_medida_alto_rf','${fld:smn_unidad_medida_alto_rf}','form1');
document.form1.sdi_ancho.value='${fld:sdi_ancho@#,###,##0.00}';
setComboValue('smn_unidad_medida_ancho_rf','${fld:smn_unidad_medida_ancho_rf}','form1');
document.form1.sdi_profundidad.value='${fld:sdi_profundidad@#,###,##0.00}';
setComboValue('smn_unidad_medida_prof_rf','${fld:smn_unidad_medida_prof_rf}','form1');
setComboValue('sdi_estatus','${fld:sdi_estatus}','form1');
document.form1.sdi_vigencia.value='${fld:sdi_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_sub_division_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.sdi_codigo.disabled=true;
 
var tipo = '${fld:sdi_es_ubicacion}';

if(tipo=="NO"){
	document.getElementById("dimension_alto").style.display="none";	
	document.getElementById("unidad_medida_alto").style.display="none";	
	document.getElementById("dimension_ancho").style.display="none";	
	document.getElementById("unidad_medida_ancho").style.display="none";
	document.getElementById("profundidad").style.display="none";	
	document.getElementById("unidad_medida_profundidad").style.display="none";	
}else if(tipo=="SI"){
	document.getElementById("dimension_alto").style.display="";	
	document.getElementById("unidad_medida_alto").style.display="";
	document.getElementById("dimension_ancho").style.display="";
	document.getElementById("unidad_medida_ancho").style.display="";
	document.getElementById("profundidad").style.display="";	
	document.getElementById("unidad_medida_profundidad").style.display="";			
}else if(tipo == ""){
	document.getElementById("dimension_alto").style.display="none";	
	document.getElementById("unidad_medida_alto").style.display="none";	
	document.getElementById("dimension_ancho").style.display="none";	
	document.getElementById("unidad_medida_ancho").style.display="none";
	document.getElementById("profundidad").style.display="none";	
	document.getElementById("unidad_medida_profundidad").style.display="none";	
}

