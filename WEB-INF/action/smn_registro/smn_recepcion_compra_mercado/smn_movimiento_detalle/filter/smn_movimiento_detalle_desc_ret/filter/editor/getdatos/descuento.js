var por = '${fld:porcentaje}';
var pordesc = '${fld:porcentajedesc}';
var cant_precio = '${fld:dyr_apli_cant_precio}';
var calcula_redondeo = '${fld:ent_calcula_redondeo}';

if (cant_precio=="TL") 
{
	document.getElementById('mdd_monto_descuento_ml').disabled = false;
}
else
{	
	document.getElementById('smn_porcentaje_rf').value = pordesc;
	document.getElementById('smn_porcentaje_rf').disabled = true;
	var monto = document.getElementById('mdd_monto_base_ml').value; 
	document.getElementById('mdd_monto_descuento_ml').disabled = true;
	total = (monto * por)/100;
	total = (total * pordesc)/100;
	if(calcula_redondeo=='SI')
	{
		document.getElementById('mdd_monto_descuento_ml').value=Math.round(total);
	}
	else
	{
		document.getElementById('mdd_monto_descuento_ml').value=total.toFixed(2);
	}
}