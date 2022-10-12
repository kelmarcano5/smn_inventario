select 
	smn_base.smn_item.smn_item_id as id, 
	smn_base.smn_item.itm_codigo|| ' - ' || smn_base.smn_item.itm_nombre as item 
from 
	smn_base.smn_item
	INNER JOIN
	smn_inventario.smn_caracteristica_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_caracteristica_item.smn_item_rf