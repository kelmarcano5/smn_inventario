SELECT
	smn_base.smn_descuentos_retenciones.smn_descuentos_retenciones_id AS id, 
	smn_base.smn_descuentos_retenciones.dyr_codigo|| ' - ' ||smn_base.smn_descuentos_retenciones.dyr_descripcion AS item 
FROM
	smn_base.smn_descuentos_retenciones