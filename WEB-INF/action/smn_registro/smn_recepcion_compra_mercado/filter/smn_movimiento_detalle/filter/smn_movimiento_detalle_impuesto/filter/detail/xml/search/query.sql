select
select
select
select
select
	imp_monto_sustraendo,
	case
	when smn_inventario.smn_movimiento_detalle_impuesto.mdi_tipo_movimiento='DE' then '${lbl:b_debit}'
	when smn_inventario.smn_movimiento_detalle_impuesto.mdi_tipo_movimiento='CR' then '${lbl:b_credit}'
	end as mdi_tipo_movimiento,
select
select
	smn_inventario.smn_movimiento_detalle_impuesto.*
from
	smn_inventario.smn_movimiento_detalle_impuesto
	left outer join smn_base.smn_codigos_impuestos on smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id = smn_inventario.smn_movimiento_detalle_impuesto.mdi_sustraendo_rf
where
	smn_mov_det_impuesto_id = ${fld:id}
