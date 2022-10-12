select
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso as mca_estatus_proceso_pl0,
select
select
select
	case
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='EN' then '${lbl:b_entry}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='SA' then '${lbl:b_exit}'
	when smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='AP' then '${lbl:b_fit_price}'
	end as mde_tipo_movimiento,
	case
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' then '${lbl:b_not}'
	end as mde_es_bonificacion,
select
select
select
	case
	when smn_inventario.smn_movimiento_detalle.mde_estatus='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='DE' then '${lbl:b_deposity}'
	when smn_inventario.smn_movimiento_detalle.mde_estatus='CN' then '${lbl:b_concilied}'
	end as mde_estatus,
	smn_inventario.smn_movimiento_detalle.*
from
	smn_inventario.smn_movimiento_cabecera,
	smn_inventario.smn_movimiento_detalle
where
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id=smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id
	and
	smn_movimiento_detalle_id = ${fld:id}
