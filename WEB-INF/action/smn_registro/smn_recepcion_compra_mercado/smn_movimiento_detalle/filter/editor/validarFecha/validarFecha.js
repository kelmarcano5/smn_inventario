var result = "<validar_fecha_rows>${fld:item}</validar_fecha_rows>";
var date = new Date();
var fecha = date.getDate()+'-'+(date.getMonth()+1)+'-'+date.getFullYear();

if(result!="")
{
	if(new Date(strrevFecha(fecha))>new Date(strrevFecha(result)))
	{
		document.getElementById('mde_fecha_vencimiento_lote').value = result;
		document.getElementById('mde_fecha_vencimiento_lote').disabled = true;
		document.getElementById('calendario_vencimiento').hidden = true;
		document.getElementById('mensaje_fecha_vencimiento').innerHTML = "El lote ya caduc&oacute;";
		document.getElementById('grabar').disabled=true;
	}
	else
	{
		document.getElementById('mde_fecha_vencimiento_lote').value = result;
		document.getElementById('mde_fecha_vencimiento_lote').disabled = true;
		document.getElementById('calendario_vencimiento').hidden = true;
		document.getElementById('mensaje_fecha_vencimiento').innerHTML = "";
		document.getElementById('grabar').disabled=false;
	}	
}
else
{
	document.getElementById('mde_fecha_vencimiento_lote').disabled = true;
	document.getElementById('calendario_vencimiento').hidden = false;
	document.getElementById('col_fecha_vencimiento').value = fecha;
	document.getElementById('mensaje_fecha_vencimiento').innerHTML = "";
	document.getElementById('grabar').disabled=false;
}

function strrevFecha(fecha){
	var array = fecha.split("-");
	var dia = array[0];
	var mes = array[1];
	var ano = array[2];
	var fecha = ano+"-"+mes+"-"+dia;
	
	return fecha; 
}