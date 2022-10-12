select smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id as id, smn_base.smn_almacen.alm_codigo|| ' - ' || smn_base.smn_almacen.alm_nombre as item from smn_base.smn_almacen 
inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_almacen_rf=smn_base.smn_almacen.smn_almacen_id order by alm_nombre
