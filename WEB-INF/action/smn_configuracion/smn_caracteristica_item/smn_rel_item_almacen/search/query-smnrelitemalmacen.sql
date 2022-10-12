select
	smn_inventario.smn_rel_item_almacen.smn_rel_item_almacen_id,
	smn_inventario.smn_caracteristica_item.cit_descripcion_tecnica as smn_caracteristica_item_id,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_rel_item_almacen.ria_cantidad_max,
smn_inventario.smn_rel_item_almacen.ria_cantidad_min,
smn_inventario.smn_rel_item_almacen.ria_punto_reorden,
smn_inventario.smn_rel_item_almacen.ria_cantidad_seguridad

	
from
	smn_inventario.smn_rel_item_almacen
	inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = 	smn_inventario.smn_rel_item_almacen.smn_almacen_rf
	inner join smn_inventario.smn_caracteristica_item on  smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id = 	smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id
WHERE
	smn_inventario.smn_rel_item_almacen.smn_caracteristica_item_id = ${fld:id2}