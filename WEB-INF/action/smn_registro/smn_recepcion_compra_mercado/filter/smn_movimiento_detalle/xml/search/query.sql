select
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	case
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='EN' then '${lbl:b_entry}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='SA' then '${lbl:b_exit}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='AP' then '${lbl:b_fit_price}'
	end as mde_tipo_movimiento,
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	end as mde_es_bonificacion,

	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_inventario.smn_movimiento_detalle.smn_item_rf,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_inventario.smn_movimiento_detalle.mde_es_bonificacion,
	smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro
	
from
	smn_inventario.smn_movimiento_detalle
