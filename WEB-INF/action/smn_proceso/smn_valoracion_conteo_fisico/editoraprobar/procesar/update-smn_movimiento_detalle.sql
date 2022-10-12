UPDATE smn_inventario.smn_movimiento_detalle SET 
	mde_cantidad_recibida = ${fld:mde_cantidad_recibida},
	mde_cantidad_por_recibir = ${fld:mde_cantidad_por_recibir},
	mde_estatus = ${fld:mde_estatus},
	mde_idioma = '${def:locale}',
  	mde_usuario = '${def:user}',
  	mde_fecha_registro = {d '${def:date}'},
  	mde_hora = '${def:time}'
WHERE
	smn_movimiento_detalle_id = (select last_value as smn_movimiento_cabecera_id from smn_inventario.seq_smn_movimiento_cabecera)