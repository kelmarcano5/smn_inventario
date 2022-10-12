var monto_base_ml = document.getElementById('mdd_monto_base_ml').value;
var monto_descuento_ml = document.getElementById('mdd_monto_descuento_ml').value;
var monto_cambio = '${fld:item}';
var total_ma = 0.0;
var total_descuento_ma = 0.0;

total = monto_base_ml/monto_cambio;
total_descuento_ma = monto_descuento_ml/monto_cambio;

document.getElementById('mdd_monto_base_ma').value=total.toFixed(2);
document.getElementById('mdd_monto_descuento_ma').value = total_descuento_ma.toFixed(2);
