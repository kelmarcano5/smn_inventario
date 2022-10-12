select
		smn_inventario.smn_caracteristica_item.cit_codigo_arancel
from
		smn_inventario.smn_caracteristica_item
where
		upper(smn_inventario.smn_caracteristica_item.cit_codigo_arancel) = upper(${fld:cit_codigo_arancel})
