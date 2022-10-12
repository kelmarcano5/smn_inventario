var result = "<validar_numero_rows>${fld:item}</validar_numero_rows>";

if(result!="")
{
	document.getElementById('mca_numero').value = "";
	alertBox ('${lbl:b_registered_number}','${lbl:b_continue_button}', null, null);
}