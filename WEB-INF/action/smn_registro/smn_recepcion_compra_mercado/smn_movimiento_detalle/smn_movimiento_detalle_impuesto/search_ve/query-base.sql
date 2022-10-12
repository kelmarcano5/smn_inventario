select
		smn_inventario.smn_movimiento_detalle_impuesto.smn_mov_det_impuesto_id,
	case
	when smn_inventario.smn_movimiento_detalle_impuesto.mdi_tipo_movimiento='DE' then '${lbl:b_debit}'
	when smn_inventario.smn_movimiento_detalle_impuesto.mdi_tipo_movimiento='CR' then '${lbl:b_credit}'
	end as mdi_tipo_movimiento,
	smn_base.smn_codigos_impuestos.imp_codigo||'-'||smn_base.smn_codigos_impuestos.imp_descripcion as smn_cod_impuesto_deduc_rf,
	smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_base,
	smn_base.smn_codigos_impuestos.imp_porcentaje_base as smn_porcentaje_impuesto_rf,
	smn_base.smn_codigos_impuestos.imp_monto_sustraendo as mdi_sustraendo_rf,
	smn_inventario.smn_movimiento_detalle_impuesto.mdi_tipo_movimiento,
	smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ml,
	smn_inventario.smn_movimiento_detalle_impuesto.mdi_monto_impuesto_ma,
	smn_inventario.smn_movimiento_detalle_impuesto.mdi_fecha_registro
	
from
	smn_inventario.smn_movimiento_detalle_impuesto
LEFT OUTER JOIN smn_base.smn_codigos_impuestos on smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id = smn_inventario.smn_movimiento_detalle_impuesto.smn_cod_impuesto_deduc_rf

where
	smn_mov_det_impuesto_id is not null
	${filter}
order by
		smn_mov_det_impuesto_id
