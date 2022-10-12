select
	smn_ubicacion_detalle_id,
	smn_ubicacion_cabecera_id,
	div_codigo ||' - '|| div_descripcion as smn_divisiones_id,
	sdi_codigo ||' - '|| sdi_descripcion as smn_subdivisiones_id,
	smn_lote_id,
	ubd_cantidad_inicial,
	ubd_entrada,
	ubd_salida,
	ubd_cantidad_final,
	ubd_idioma,
	ubd_usuario,
	ubd_fecha_registro,
	ubd_hora
from
	smn_inventario.smn_ubicacion_detalle
	inner join smn_inventario.smn_division on smn_division_id = smn_divisiones_id
	inner join smn_inventario.smn_sub_division on smn_sub_division_id = smn_subdivisiones_id
where
	smn_ubicacion_detalle_id is not null and
	smn_ubicacion_cabecera_id = ${fld:id2}
	${filter}
order by
		smn_ubicacion_detalle_id
