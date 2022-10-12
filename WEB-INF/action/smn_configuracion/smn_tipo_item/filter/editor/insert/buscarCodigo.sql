select
		smn_inventario.smn_tipo_item.tip_codigo
from
		smn_inventario.smn_tipo_item
where
		upper(smn_inventario.smn_tipo_item.tip_codigo) = upper(${fld:tip_codigo})
