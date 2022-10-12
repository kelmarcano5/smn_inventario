var interruptor=0;
<descuentos_rows>
	if (interruptor==0) 
	{
		interruptor=1;
		listboxClear('descuento');
	}
	selectDescuentos('${fld:id}','${fld:item}','${fld:dyr_porcentaje_base}','${fld:dyr_porcentaje_descuento}','${fld:dyr_apli_cant_precio}','AG');
</descuentos_rows>