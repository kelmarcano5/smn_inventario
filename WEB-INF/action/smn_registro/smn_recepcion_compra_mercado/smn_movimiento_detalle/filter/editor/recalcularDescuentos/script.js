var porcentaje_base = '${fld:dyr_porcentaje_base}';
var porcentaje_calculo = '${fld:dyr_porcentaje_descuento}';

if ('${fld:dyr_apli_cant_precio}'=="TL")
{
	porcentaje_calculo=$("#porc_descuento_libre").val();
	$("#monto_descuento_libre").val("");
	$("#monto_descuento").css("display","none");
}

calcularDescuentos(porcentaje_base,porcentaje_calculo,'${fld:accion}');