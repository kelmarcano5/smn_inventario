select smn_base.smn_item.smn_item_id as id, 
smn_base.smn_item.itm_codigo|| ' - ' || smn_base.smn_item.itm_nombre as item 
from smn_base.smn_item
inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_item_rf = smn_base.smn_item.smn_item_id