var lo = '${fld:lote}';

if(lo == 'SI'){
	document.getElementById('lote').style.display='';
	document.getElementById('fvenc').style.display='';
	document.getElementById('fecha_vencimiento_lote').disabled = true;
	document.getElementById('calendario_vencimiento').hidden = true;
}else{
	document.getElementById('lote').style.display='none';
	document.getElementById('fvenc').style.display='none';
	document.getElementById('mde_lote').value=0;
}