select 
	smn_inventario.smn_caracteristica_item.cit_req_control_lote as lote 
from 
	smn_inventario.smn_caracteristica_item
where 
	smn_inventario.smn_caracteristica_item.smn_item_rf=${fld:id}