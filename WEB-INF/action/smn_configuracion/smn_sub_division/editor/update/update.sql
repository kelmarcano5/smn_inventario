UPDATE smn_inventario.smn_sub_division SET
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_division_id=${fld:smn_division_id},
	sdi_codigo=${fld:sdi_codigo},
	sdi_descripcion=${fld:sdi_descripcion},
	sdi_es_ubicacion=${fld:sdi_es_ubicacion},
	sdi_alto=${fld:sdi_alto},
	smn_unidad_medida_alto_rf=${fld:smn_unidad_medida_alto_rf},
	sdi_ancho=${fld:sdi_ancho},
	smn_unidad_medida_ancho_rf=${fld:smn_unidad_medida_ancho_rf},
	sdi_profundidad=${fld:sdi_profundidad},
	smn_unidad_medida_prof_rf=${fld:smn_unidad_medida_prof_rf},
	sdi_estatus=${fld:sdi_estatus},
	sdi_vigencia=${fld:sdi_vigencia},
	sdi_idioma='${def:locale}',
	sdi_usuario='${def:user}',
	sdi_fecha_registro={d '${def:date}'},
	sdi_hora='${def:time}'

WHERE
	smn_sub_division_id=${fld:id}

