select 
	smn_base.smn_descuentos_retenciones.dyr_porcentaje_base as porcentaje,
	smn_base.smn_descuentos_retenciones.dyr_porcentaje_descuento as porcentajedesc
FROM
	smn_base.smn_descuentos_retenciones
where
smn_descuentos_retenciones_id=${fld:id}