select 
	smn_base.smn_centro_costo.smn_centro_costo_id as id, 
	smn_base.smn_centro_costo.cco_codigo|| ' - ' || smn_base.smn_centro_costo.cco_descripcion_corta as item 
from 
	smn_base.smn_centro_costo
inner join 
	smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_centro_costo_rf = smn_base.smn_centro_costo.smn_centro_costo_id
where 
	smn_inventario.smn_caracteristica_item.smn_item_rf=${fld:smn_item_rf}