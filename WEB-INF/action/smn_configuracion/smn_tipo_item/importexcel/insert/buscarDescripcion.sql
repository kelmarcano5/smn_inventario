select
		smn_inventario.smn_tipo_item.tip_descripcion
from
		smn_inventario.smn_tipo_item
where
		upper(smn_inventario.smn_tipo_item.tip_descripcion) = upper(${fld:tip_descripcion})
