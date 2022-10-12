select
select
select
	case
	when smn_inventario.smn_item_almacen.ria_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_item_almacen.ria_estatus='IN' then '${lbl:b_inactive}'
	end as ria_estatus_combo,
	smn_inventario.smn_item_almacen.smn_item_rf,
	smn_inventario.smn_item_almacen.smn_almacen_rf,
	smn_inventario.smn_item_almacen.ria_estatus,
	smn_inventario.smn_item_almacen.ria_vigencia,
	smn_inventario.smn_item_almacen.ria_idioma,
	smn_inventario.smn_item_almacen.ria_usuario,
	smn_inventario.smn_item_almacen.ria_fecha_registro,
	smn_inventario.smn_item_almacen.ria_hora,
		smn_inventario.smn_item_almacen.smn_item_almacen_id
from
	smn_inventario.smn_item_almacen
where
	smn_item_almacen_id = ${fld:id}
