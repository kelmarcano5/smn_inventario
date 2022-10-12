var result = "<validar_recibo_rows>${fld:item}</validar_recibo_rows>";

if(result!="")
{
	document.getElementById('mca_recibo').value = "";
	document.getElementById('mensaje_mca_recibo').innerHTML = "Recibo ya registrado con ese proveedor";
}
else
{
	document.getElementById('mensaje_mca_recibo').innerHTML = "";
}
