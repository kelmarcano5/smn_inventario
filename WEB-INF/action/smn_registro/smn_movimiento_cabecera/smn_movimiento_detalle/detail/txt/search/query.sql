select
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	end as mde_es_bonificacion_combo,
select
	case
	when smn_inventario.smn_movimiento_detalle.mde_estatus='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='DE' then '${lbl:b_deposity}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='CN' then '${lbl:b_concilied}'
	end as mde_estatus_combo,
	smn_inventario.smn_movimiento_detalle.*
from
	smn_inventario.smn_movimiento_detalle
where
	smn_movimiento_detalle_id = ${fld:id}
