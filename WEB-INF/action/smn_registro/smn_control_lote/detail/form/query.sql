select
	smn_inventario.smn_control_lote.smn_control_lote_id,
	smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_almacen.alm_nombre as smn_caracteristica_almacen_id,
	smn_base.smn_item.itm_nombre as smn_caracteristica_item_id,
	smn_inventario.smn_control_lote.col_lote,
	smn_inventario.smn_control_lote.col_fecha_vencimiento,
	smn_inventario.smn_control_lote.col_cantidad_inicial,
	smn_inventario.smn_control_lote.col_entradas,
	smn_inventario.smn_control_lote.col_salidas,
	smn_inventario.smn_control_lote.col_cantidad_final,
	smn_inventario.smn_control_lote.col_fecha_registro,
	smn_inventario.smn_control_lote.col_idioma,
	smn_inventario.smn_control_lote.col_hora,
	smn_inventario.smn_control_lote.col_usuario	
from
	smn_inventario.smn_control_lote
inner join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id=smn_inventario.smn_control_lote.smn_entidad_rf
inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id=smn_inventario.smn_control_lote.smn_caracteristica_almacen_id
inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id=smn_inventario.smn_caracteristica_almacen.smn_almacen_rf
inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=smn_inventario.smn_control_lote.smn_caracteristica_item_id
inner join smn_base.smn_item on smn_base.smn_item.smn_item_id=smn_inventario.smn_caracteristica_item.smn_item_rf
left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id=smn_inventario.smn_control_lote.smn_sucursal_rf
where
	smn_control_lote_id = ${fld:id}
