select
		smn_inventario.smn_tipo_item.tip_codigo,
	smn_inventario.smn_tipo_item.tip_descripcion,
	smn_inventario.smn_tipo_item.tip_estatus,
	smn_inventario.smn_tipo_item.tip_fecha_registro
from
	smn_inventario.smn_tipo_item 
where
	smn_inventario.smn_tipo_item.smn_tipo_item_id = ${fld:id}
