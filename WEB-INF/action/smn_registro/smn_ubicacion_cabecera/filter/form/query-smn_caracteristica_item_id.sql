select smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id as id, smn_base.smn_item.itm_codigo|| ' - ' || smn_base.smn_item.itm_nombre as item 
from smn_inventario.smn_caracteristica_item
inner join smn_base.smn_item on smn_base.smn_item.smn_item_id=smn_inventario.smn_caracteristica_item.smn_item_rf
order by itm_nombre