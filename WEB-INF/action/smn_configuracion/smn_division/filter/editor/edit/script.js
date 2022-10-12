document.form1.div_codigo.value='${fld:div_codigo@js}';
document.form1.div_descripcion.value='${fld:div_descripcion@js}';
setComboValue('div_estatus','${fld:div_estatus}','form1');
document.form1.div_vigencia.value='${fld:div_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_division_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.div_codigo.disabled=true;
 

