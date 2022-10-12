document.form1.con_codigo.value='${fld:con_codigo@js}';
document.form1.con_descripcion.value='${fld:con_descripcion@js}';
setComboValue('smn_almacen_rf','${fld:smn_almacen_rf}','form1');
setComboValue('smn_ubicacion_id','${fld:smn_ubicacion_id}','form1');
setComboValue('smn_rol_1_id','${fld:smn_rol_1_id}','form1');
setComboValue('smn_rol_2_id','${fld:smn_rol_2_id}','form1');
setComboValue('con_estatus','${fld:con_estatus}','form1');
document.form1.con_fecha_vigencia.value='${fld:con_fecha_vigencia@dd-MM-yyyy}';
document.form1.id.value='${fld:smn_conteo_id@#,###,###}';
 
document.getElementById("formTitle").innerHTML = "${lbl:b_edit_record}";
document.getElementById("grabar").disabled=false;
setFocusOnForm("form1");


document.form1.con_codigo.disabled=true;
 

