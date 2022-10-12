UPDATE smn_inventario.smn_despacho SET
	smn_zona_rf=${fld:smn_zona_rf},
	smn_ruta_id=${fld:smn_ruta_id},
	smn_transporte_id=${fld:smn_transporte_id},
	smn_almacen_rf=${fld:smn_almacen_rf},
	smn_direccion_rf=${fld:smn_direccion_rf}

WHERE
	smn_despacho_id=${fld:id}

