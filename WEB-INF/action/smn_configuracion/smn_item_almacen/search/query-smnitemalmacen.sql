select
	(select smn_base.smn_item.itm_codigo|| ' - ' || smn_base.smn_item.itm_nombre from  smn_base.smn_item  where smn_base.smn_item.smn_item_id is not null  and smn_base.smn_item.smn_item_id=smn_inventario.smn_item_almacen.smn_item_rf  order by itm_nombre ) as smn_item_rf_combo,
	(select smn_base.smn_almacen.alm_codigo|| ' - ' || smn_base.smn_almacen.alm_nombre from  smn_base.smn_almacen  where smn_base.smn_almacen.smn_almacen_id is not null  and smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_item_almacen.smn_almacen_rf  order by alm_nombre ) as smn_almacen_rf_combo,
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
order by
		smn_item_almacen_id
