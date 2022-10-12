document.form1.tdc_codigo.value='${fld:tdc_codigo@js}';
document.form1.tdc_nombre.value='${fld:tdc_nombre@js}';
setComboValue('tdc_naturaleza','${fld:tdc_naturaleza}','form1');
document.form1.tdc_vigencia.value='${fld:tdc_vigencia@dd-MM-yyyy}';
setComboValue('tdc_estatus','${fld:tdc_estatus}','form1');
document.form1.id.value='${fld:smn_tipo_documento_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.tdc_codigo.disabled=true;
 

