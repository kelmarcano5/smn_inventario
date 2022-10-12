select 
	cit_tipo_costo as item 
from 
	smn_inventario.smn_caracteristica_item
WHERE
	 smn_inventario.smn_caracteristica_item.smn_item_rf = ${fld:smn_item_rf}