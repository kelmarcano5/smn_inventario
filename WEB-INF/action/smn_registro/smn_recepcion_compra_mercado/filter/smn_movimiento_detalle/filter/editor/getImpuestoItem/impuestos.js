var interruptor=0;
<impuestos_rows>
	if (interruptor==0) 
	{
		interruptor=1;
		listboxClear('impuesto');
	}
	selectImpuestos('${fld:id}','${fld:item}','${fld:imp_tipo_codigo}','${fld:imp_porcentaje_base}','${fld:imp_porcentaje_calculo}','${fld:imp_monto_sustraendo}','AG');
</impuestos_rows>