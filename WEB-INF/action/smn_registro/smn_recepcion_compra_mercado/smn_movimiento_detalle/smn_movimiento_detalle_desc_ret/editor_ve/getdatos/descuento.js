var por = '${fld:porcentaje}';
var pordesc = '${fld:porcentajedesc}';

document.getElementById('smn_porcentaje_rf').value = pordesc;
document.getElementById('smn_porcentaje_rf').disabled = true;
var monto = document.getElementById('mdd_monto_base_ml').value; 

total = (monto * por)/100;
total = (total * pordesc)/100;

document.getElementById('mdd_monto_descuento_ml').value=total;
document.getElementById('mdd_monto_descuento_ml').disabled = false;
