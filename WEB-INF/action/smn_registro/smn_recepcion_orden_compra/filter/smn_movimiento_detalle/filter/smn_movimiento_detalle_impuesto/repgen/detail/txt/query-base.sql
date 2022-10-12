select
	imp_monto_sustraendo,
	${field}
from
	smn_inventario.smn_movimiento_detalle_impuesto
	left outer join smn_base.smn_codigos_impuestos on smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id = smn_inventario.smn_movimiento_detalle_impuesto.mdi_sustraendo_rf
where
		smn_inventario.smn_movimiento_detalle_impuesto.smn_movimiento_detalle_impuesto_id = ${fld:id}
	
