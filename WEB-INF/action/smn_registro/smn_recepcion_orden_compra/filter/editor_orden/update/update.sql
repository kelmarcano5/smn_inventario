UPDATE smn_inventario.smn_movimiento_cabecera SET
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_documento_id=${fld:smn_documento_id},
	mca_recibo=${fld:mca_recibo},
	mca_fecha_recibida={d '${def:date}'},
	mca_idioma='${def:locale}',
	mca_usuario='${def:user}',
	mca_fecha_registro={d '${def:date}'},
	mca_hora='${def:time}'
WHERE
	smn_movimiento_cabecera_id=${fld:id}

