var monto_impuesto_ml = document.getElementById('mdi_monto_impuesto_ml').value;
var monto_cambio = '${fld:item}';
var total_impuesto_ma = 0.0;

total_impuesto_ma = monto_impuesto_ml/monto_cambio;

document.getElementById('mdi_monto_impuesto_ma').value = total_impuesto_ma.toFixed(2);
