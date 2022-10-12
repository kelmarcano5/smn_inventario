var centro_costo_name = '${fld:item}';
var centro_costo_id = '${fld:id}';

if(centro_costo_id != ""){
	document.getElementById('smn_centro_costo_rf').value = centro_costo_id;
	document.getElementById('smn_centro_costo_name').value = centro_costo_name;
}
else
{
	document.getElementById('smn_centro_costo_rf').value = "";
	document.getElementById('smn_centro_costo_name').value = "";
}
