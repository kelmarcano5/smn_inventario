UPDATE smn_inventario.smn_despacho SET
	des_estatus = 'PD',
	smn_zona_rf=${fld:smn_zona_rf},
	smn_ruta_id=${fld:smn_ruta_id},
	smn_transporte_id=${fld:smn_transporte_id},
	des_fecha_despacho={d '${def:date}'}
WHERE
	smn_despacho_id = ${fld:smn_despacho_id}
RETURNING smn_despacho_id as despachoId;