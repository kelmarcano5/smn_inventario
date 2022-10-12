SELECT
	smn_base.smn_descuentos_retenciones.smn_descuentos_retenciones_id AS id,
	smn_base.smn_descuentos_retenciones.dyr_codigo||' - '||smn_base.smn_descuentos_retenciones.dyr_descripcion AS item,
	smn_base.smn_descuentos_retenciones.dyr_porcentaje_base,
	smn_base.smn_descuentos_retenciones.dyr_porcentaje_descuento,
	smn_base.smn_descuentos_retenciones.dyr_apli_cant_precio
FROM 
	smn_base.smn_rel_item_desc_retenc 
	INNER JOIN 
	smn_base.smn_descuentos_retenciones ON smn_base.smn_rel_item_desc_retenc.smn_descuentos_retenciones_id = smn_base.smn_descuentos_retenciones.smn_descuentos_retenciones_id
WHERE 
	smn_base.smn_rel_item_desc_retenc.smn_item_id = ${fld:smn_item_rf}