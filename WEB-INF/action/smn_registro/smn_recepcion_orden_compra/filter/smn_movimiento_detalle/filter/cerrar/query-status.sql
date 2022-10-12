UPDATE smn_inventario.smn_movimiento_detalle SET 
	/*mde_estatus = (CASE WHEN (mde_estatus = 'AC') THEN 'IN'
			ELSE 'AC'
 END),*/
 	mde_estatus = 'CE',
 	mde_idioma='${def:locale}',
	mde_usuario='${def:user}',
	mde_fecha_registro={d '${def:date}'},
	mde_hora='${def:time}'
WHERE smn_movimiento_detalle_id = ${fld:id}