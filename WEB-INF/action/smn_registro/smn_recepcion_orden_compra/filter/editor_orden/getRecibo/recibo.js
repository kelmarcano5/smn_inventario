var mca_recibo = "<mca_recibo_rows>${fld:mca_recibo}</mca_recibo_rows>";
var crp_numero_documento = "<crp_numero_documento_rows>${fld:crp_numero_documento}</crp_numero_documento_rows>";

document.getElementById('mensajeRecibo').innerHTML="";

if(mca_recibo != "" || crp_numero_documento != "")
{
	document.getElementById('mca_recibo').value="";
	document.getElementById('mensajeRecibo').innerHTML="El numero de documento esta registrado";
	document.getElementById('mca_recibo').focus();
}
