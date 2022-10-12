var lo = '${fld:lote}';
document.getElementById('requiere_lote').value = lo;

if(lo == 'SI'){
	document.getElementById('lote').style.display='';
	document.getElementById('fveclote').style.display='';
	document.getElementById('mde_fecha_vencimiento_lote').disabled = true;
	document.getElementById('calendario_vencimiento').hidden = true;
}else{
	document.getElementById('lote').style.display='none';
	document.getElementById('fveclote').style.display='none';
}