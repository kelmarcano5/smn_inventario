UPDATE smn_inventario.smn_movimiento_detalle SET
	mde_cantidad_recibida=${fld:mde_cantidad_recibida},
	mde_cantidad_solicitada=${fld:mde_cantidad_solicitada},
	mde_valor_unitario_ml=${fld:mde_valor_unitario_ml},
	mde_valor_unitario_ml_origen=${fld:mde_valor_unitario_ml_origen}
WHERE
	smn_movimiento_detalle_id=${fld:smn_movimiento_detalle_id}