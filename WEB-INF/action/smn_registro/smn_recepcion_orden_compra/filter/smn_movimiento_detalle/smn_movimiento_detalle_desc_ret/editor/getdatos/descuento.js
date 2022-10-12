//var sus = '${fld:sustraendo}';
var por = '${fld:porcentaje}';
//var mtb = '${fld:monto_bruto}';
 //alert("por: "+ por);
//document.getElementById('mdi_sustraendo_rf').value = sus;
//document.getElementById('mdi_sustraendo_rf').disabled = true;

document.getElementById('smn_porcentaje_rf').value = por;
document.getElementById('smn_porcentaje_rf').disabled = true;
 var monto = document.getElementById('mdd_monto_base_ml').value;
 // alert("monto: "+ monto);
// var total = 0;
// var total1 = 0;
total = monto * por;
//total1 = parseFloat(total) - parseFloat(sus);
//alert(total);
document.getElementById('mdd_monto_descuento_ml').value=total;
document.getElementById('mdd_monto_descuento_ml').disabled = true;
