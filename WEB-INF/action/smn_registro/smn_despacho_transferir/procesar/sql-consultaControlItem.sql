SELECT * FROM smn_inventario.smn_control_item 
WHERE smn_inventario.smn_control_item.smn_item_id = ${fld:smn_item_rf} and
smn_almacen_id=${fld:smn_almacen_rf}
order by coi_fecha_movimiento desc
limit 1
