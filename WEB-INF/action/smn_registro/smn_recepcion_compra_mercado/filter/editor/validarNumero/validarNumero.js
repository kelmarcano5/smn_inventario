var result = "<validar_numero_rows>${fld:item}</validar_numero_rows>";

if(result!="")
{
	document.getElementById('mca_numero').value = "";
	document.getElementById('mensaje_mca_numero').innerHTML = "N&uacute;mero ya registrado con ese proveedor";
}
else
{
	document.getElementById('mensaje_mca_numero').innerHTML = "";
}
