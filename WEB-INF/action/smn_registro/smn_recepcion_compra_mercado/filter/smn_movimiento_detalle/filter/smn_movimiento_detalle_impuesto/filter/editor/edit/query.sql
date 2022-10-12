select	
	*
from 
	smn_inventario.smn_movimiento_detalle_impuesto
	INNER JOIN
	smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle_impuesto.smn_movimiento_detalle_id = smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id
where 
	smn_inventario.smn_movimiento_detalle_impuesto.smn_mov_det_impuesto_id = ${fld:id}


