INSERT INTO smn_inventario.smn_ubicacion_cabecera
(
	smn_ubicacion_cabecera_id,
  	smn_movimiento_detalle_id,
  	smn_caracteristica_almacen_id,
  	smn_caracteristica_item_id,
  	ubc_estatus,
  	ubc_lote,
  	ubc_cantidad_procesada,
  	ubc_fecha_recibida,
  	ubc_idioma,
  	ubc_usuario,
  	ubc_fecha_registro,
 	ubc_hora
)
VALUES
(
	nextval('smn_inventario.seq_smn_ubicacion_cabecera'), --smn_ubicacion_cabecera_id
  	${fld:smn_movimiento_detalle_id}, --smn_movimiento_detalle_id
  	${fld:smn_caracteristica_almacen_id}, --smn_caracteristica_almacen_id
  	${fld:smn_caracteristica_item_id}, --smn_caracteristica_item_id
  	'GE', --ubc_estatus
  	${fld:mde_lote}, --ubc_lote
  	${fld:mde_cantidad_por_recibir}, --ubc_cantidad_procesada
  	${fld:mca_fecha_recibida}, --ubc_fecha_recibida
  	'${def:locale}', --ubc_idioma
  	'${def:user}', --ubc_usuario
  	{d '${def:date}'}, --ubc_fecha_registro
 	 '${def:time}'--ubc_hora
)

RETURNING smn_ubicacion_cabecera_id;