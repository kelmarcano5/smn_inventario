var result = "<validar_recibo_rows>${fld:item}</validar_recibo_rows>";

if(result!="")
{
	document.getElementById('mca_recibo').value = "";
	alertBox ('${lbl:b_receipted_number}','${lbl:b_continue_button}', null, null);
}