select
case
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_item_compuesto_detalle.icd_estatus='IN' then '${lbl:b_inactive}'
	end as icd_estatus,
	smn_inventario.smn_item_compuesto_detalle.*
from
	smn_inventario.smn_item_compuesto_detalle
where
	smn_item_compuesto_detalle_id = ${fld:id}
