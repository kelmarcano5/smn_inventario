select	
	(select smn_inventario.smn_caracteristica_item.cit_descripcion_tecnica from  smn_inventario.smn_caracteristica_item where smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id is not null  and smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=smn_inventario.smn_rel_item_almacen.smn_caracteristica_item) as smn_caracteristica_item_combo,
	(select smn_base.smn_almacen.alm_codigo|| ' - ' || smn_base.smn_almacen.alm_nombre from  smn_base.smn_almacen  where smn_base.smn_almacen.smn_almacen_id is not null  and smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_rel_item_almacen.smn_almacen_rf  order by alm_nombre ) as smn_almacen_rf_combo,
	smn_inventario.smn_rel_item_almacen.smn_caracteristica_item,
	smn_inventario.smn_rel_item_almacen.smn_almacen_rf,
	smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id

from
	smn_inventario.smn_rel_item_almacen
where
	smn_rel_item_almacen_id is not null	
 	 	${filter}
order by 
	smn_rel_item_almacen_id