select
		smn_inventario.smn_caracteristica_item.cit_descripcion_compra
from
		smn_inventario.smn_caracteristica_item
where
		upper(smn_inventario.smn_caracteristica_item.cit_descripcion_compra) = upper(${fld:cit_descripcion_compra})
