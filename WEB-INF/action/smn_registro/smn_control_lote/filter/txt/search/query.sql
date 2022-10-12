select
		smn_inventario.smn_control_lote.smn_control_lote_id,
		
	smn_inventario.smn_control_lote.smn_entidad_rf,
	smn_inventario.smn_control_lote.smn_sucursal_rf,
	smn_inventario.smn_control_lote.smn_caracteristica_almacen_id,
	smn_inventario.smn_control_lote.smn_caracteristica_item_id,
	smn_inventario.smn_control_lote.col_lote,
	smn_inventario.smn_control_lote.col_fecha_vencimiento,
	smn_inventario.smn_control_lote.col_cantidad_inicial,
	smn_inventario.smn_control_lote.col_entradas,
	smn_inventario.smn_control_lote.col_salidas,
	smn_inventario.smn_control_lote.col_cantidad_final
	
from
	smn_inventario.smn_control_lote
