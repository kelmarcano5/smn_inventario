SELECT
	dyr_porcentaje_base,
	dyr_porcentaje_descuento,
	dyr_apli_cant_precio	
FROM
	smn_base.smn_descuentos_retenciones
WHERE
	smn_descuentos_retenciones_id = ${fld:smn_descuentos_retenciones_id}
