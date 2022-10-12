select	
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id, 
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso as mca_estatus_proceso_pl0,
	smn_inventario.smn_control_recepcion_parcial.*
from
	smn_inventario.smn_movimiento_cabecera,
	smn_inventario.smn_control_recepcion_parcial 
where
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=smn_inventario.smn_control_recepcion_parcial.smn_movimiento_cabecera_id and
	smn_control_recepcion_id = ${fld:id} 
	


