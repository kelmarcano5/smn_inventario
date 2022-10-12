var costo = '${fld:item}';

if (costo == 'MA' && $("#mde_es_bonificacion").val()!="SI") 
{
	document.getElementById('moneda').style.display="";
	document.getElementById('tasa').style.display="";
	document.getElementById('smn_tasa_rf').disabled=true;
	document.getElementById('mde_valor_unitario_ml').disabled=true;
	document.getElementById('valor_unitario_ma').style.display="";
	document.getElementById('monto_bruto_ma').style.display="";
	document.getElementById('monto_disminucion_ma').style.display="";
	document.getElementById('valor_agregado_ma').style.display="";
	document.getElementById('monto_neto_ma').style.display="";

}
else
{
	document.getElementById('moneda').style.display="none";
	document.getElementById('tasa').style.display="none";
	document.getElementById('mde_valor_unitario_ml').disabled=false;
	document.getElementById('valor_unitario_ma').style.display="none";
	document.getElementById('monto_bruto_ma').style.display="none";
	document.getElementById('monto_disminucion_ma').style.display="none";
	document.getElementById('valor_agregado_ma').style.display="none";
	document.getElementById('monto_neto_ma').style.display="none";
}