select	
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id, 
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso as mca_estatus_proceso_pl0,
	smn_inventario.smn_movimiento_detalle.*,
	smn_base.smn_centro_costo.smn_centro_costo_id, 
	smn_base.smn_centro_costo.cco_codigo|| ' - ' || smn_base.smn_centro_costo.cco_descripcion_corta as smn_centro_costo_combo 
from
	smn_inventario.smn_movimiento_cabecera,
	smn_inventario.smn_movimiento_detalle
	INNER JOIN
	smn_base.smn_centro_costo ON smn_inventario.smn_movimiento_detalle.smn_centro_costo_rf = smn_base.smn_centro_costo.smn_centro_costo_id
where
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id and
	smn_movimiento_detalle_id = ${fld:id} 
	


