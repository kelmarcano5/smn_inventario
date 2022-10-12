INSERT INTO smn_inventario.smn_despacho
(
	smn_despacho_id,
	smn_zona_rf,
	smn_ruta_id,
	smn_transporte_id,
	smn_almacen_rf,
	smn_direccion_rf
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_despacho},
	${fld:smn_zona_rf},
	${fld:smn_ruta_id},
	${fld:smn_transporte_id},
	${fld:smn_almacen_rf},
	${fld:smn_direccion_rf}
)
