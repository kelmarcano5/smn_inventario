select 
	smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id as id, 
	smn_base.smn_tasas_de_cambio.tca_tasa_cambio AS item,
	smn_base.smn_tasas_de_cambio.tca_fecha_vigencia as fecha_item 
from 
	smn_base.smn_tasas_de_cambio
WHERE
	smn_monedas_id = ${fld:id}
	AND
	tca_fecha_vigencia >= CURRENT_DATE;