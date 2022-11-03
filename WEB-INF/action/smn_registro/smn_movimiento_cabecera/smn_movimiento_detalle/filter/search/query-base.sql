select
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	end as mde_es_bonificacion_combo,
	case
	when smn_inventario.smn_movimiento_detalle.mde_estatus='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='DE' then '${lbl:b_deposity}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='CN' then '${lbl:b_concilied}'
	end as mde_estatus_combo,
	smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id,
	smn_inventario.smn_movimiento_detalle.mde_es_bonificacion,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_descripcion,
	smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf,
	smn_inventario.smn_movimiento_detalle.smn_precio_ml,
	smn_inventario.smn_movimiento_detalle.smn_precio_ma,
	smn_inventario.smn_movimiento_detalle.mde_estatus,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro,
		smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id
	
from
	smn_inventario.smn_movimiento_detalle
where
	smn_movimiento_detalle_id is not null
	${filter}
order by
		smn_movimiento_detalle_id
