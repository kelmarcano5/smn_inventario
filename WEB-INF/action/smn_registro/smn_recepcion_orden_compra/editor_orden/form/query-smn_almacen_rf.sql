select 
	smn_base.smn_almacen.smn_almacen_id as id, 
	smn_base.smn_almacen.alm_codigo || ' - ' || smn_base.smn_almacen.alm_nombre as item 
from 
	smn_base.smn_almacen 
INNER JOIN
	smn_inventario.smn_movimiento_cabecera ON smn_base.smn_almacen.alm_empresa = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
INNER JOIN
	smn_inventario.smn_caracteristica_almacen ON smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_caracteristica_almacen.smn_almacen_rf
WHERE
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = ${fld:id}
AND
	smn_inventario.smn_caracteristica_almacen.cal_tipo_almacen = 'RE'
order by smn_base.smn_almacen.alm_nombre