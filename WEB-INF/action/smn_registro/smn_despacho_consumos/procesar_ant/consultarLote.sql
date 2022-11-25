SELECT smn_control_lote_id AS id_lote,
    col_fecha_vencimiento AS fecha_venc 
FROM smn_inventario.smn_despacho_detalle
	INNER JOIN smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id
	INNER JOIN smn_inventario.smn_despacho on smn_inventario.smn_despacho.smn_despacho_id=smn_inventario.smn_despacho_detalle.smn_despacho_id	  	
	INNER JOIN smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_almacen_rf=smn_inventario.smn_despacho.smn_almacen_rf
  	LEFT JOIN smn_inventario.smn_control_lote on smn_inventario.smn_control_lote.smn_caracteristica_almacen_id=smn_inventario.smn_caracteristica_almacen.smn_caracteristica_almacen_id 
WHERE 
	smn_inventario.smn_control_lote.smn_caracteristica_item_id=smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id 
	AND
	smn_inventario.smn_control_lote.col_cantidad_final > 0 
	AND
	smn_inventario.smn_control_lote.col_fecha_vencimiento > {d '${def:date}'} 
	AND
	smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id=${fld:smn_despacho_detalle_id}
	ORDER BY col_fecha_vencimiento ASC
LIMIT 1;