SELECT
	tca_tasa_cambio as item
FROM
	smn_base.smn_tasas_de_cambio
WHERE
	smn_tasas_de_cambio_id = ${fld:id}