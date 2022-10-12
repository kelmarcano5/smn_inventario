var sus = '${fld:sustraendo}';
var por = '${fld:porcentaje}';
var porbase = '${fld:porcbase}';
var tipocod = "${fld:tipocod}";
var calcula_redondeo = '${fld:ent_calcula_redondeo}';
var monto = document.getElementById('mdi_monto_base').value;

if (sus=="") 
	sus=0;

//var mtb = '${fld:monto_bruto}';
 //alert(sus+"por: "+ por);
document.getElementById('mdi_sustraendo_rf').value = sus;
document.getElementById('mdi_sustraendo_rf').disabled = true;
document.getElementById('smn_porcentaje_impuesto_rf').value = por;
document.getElementById('smn_porcentaje_impuesto_rf').disabled = true;
 
// var total = 0;
// var total1 = 0;
total = (monto*porbase)/100;
total = (total*por)/100;
total1 = parseFloat(total) - parseFloat(sus);
//alert(total1);

if(tipocod == 'RE')
	total1 = total1*-1;

if(calcula_redondeo=='SI')
	document.getElementById('mdi_monto_impuesto_ml').value=Math.round(total1);
else
	document.getElementById('mdi_monto_impuesto_ml').value=total1.toFixed(2);

document.getElementById('mdi_monto_impuesto_ml').disabled = false;