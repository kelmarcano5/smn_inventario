select	
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id, 
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso as mca_estatus_proceso_pl0,
	smn_inventario.smn_movimiento_detalle.*
from
	smn_inventario.smn_movimiento_cabecera,
	smn_inventario.smn_movimiento_detalle 
where
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id and
	smn_movimiento_detalle_id = ${fld:id} 
	


