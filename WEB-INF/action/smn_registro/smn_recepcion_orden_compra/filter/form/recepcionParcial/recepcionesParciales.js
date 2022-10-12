var control_recepcion_parcial = "<smn_control_recepcion_parcial_rows>${fld:smn_movimiento_cabecera_id}</smn_control_recepcion_parcial_rows>";

if(control_recepcion_parcial)
{
	controlRecepcionesParciales(control_recepcion_parcial);
}
else
{
	alertBox("${lbl:recepcion_parcial_empty}",'${lbl:b_continue_button}',null,'search();');
}