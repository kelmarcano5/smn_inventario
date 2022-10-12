SELECT smn_inventario.smn_movimiento_cabecera.*, cal_tipo_calculo_costo
 FROM smn_inventario.smn_movimiento_cabecera 
 inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_almacen_rf=smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
WHERE 
	smn_movimiento_cabecera_id = ${fld:smn_movimiento_cabecera_id} 
	AND
	mca_estatus_proceso = 'RE'