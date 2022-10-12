select
	smn_inventario.smn_conteo_inventario_fisico.smn_item_id
from
	smn_inventario.smn_conteo_inventario_fisico
where
	smn_inventario.smn_conteo_inventario_fisico.smn_item_id=${fld:smn_item_id} limit 1 
