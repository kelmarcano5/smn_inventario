INSERT INTO smn_inventario.smn_ubicacion_detalle
(
	smn_ubicacion_detalle_id,
  	smn_ubicacion_cabecera_id,
  	smn_divisiones_id,
  	smn_subdivisiones_id,
  	ubd_entrada,
  	ubd_salida,
  	ubd_idioma,
  	ubd_usuario,
 	ubd_fecha_registro,
  	ubd_hora
)
VALUES
(
	nextval('smn_inventario.seq_smn_ubicacion_detalle'), --smn_ubicacion_detalle_id
  	${fld:smn_ubicacion_cabecera_id}, --smn_ubicacion_cabecera_id
  	0, --smn_divisiones_id
  	0, --smn_subdivisiones_id
  	${fld:mde_cantidad_por_recibir}, --ubd_entrada
  	0, --ubd_salida
  	'${def:locale}', --ubd_idioma
  	'${def:user}', --ubd_usuario
 	{d '${def:date}'}, --ubd_fecha_registro
  	'${def:time}' --ubd_hora
)