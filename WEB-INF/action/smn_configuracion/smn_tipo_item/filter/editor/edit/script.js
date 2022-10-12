document.form1.tip_codigo.value='${fld:tip_codigo@js}';
document.form1.tip_descripcion.value='${fld:tip_descripcion@js}';
setComboValue('tip_estatus','${fld:tip_estatus}','form1');
document.form1.id.value='${fld:smn_tipo_item_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.tip_codigo.disabled=true;
 

