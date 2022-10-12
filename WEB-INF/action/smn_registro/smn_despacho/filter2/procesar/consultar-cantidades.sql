SELECT SUM(dde_cantidad_solicitada) AS cantidad_solicitada,
	   SUM(dde_cantidad_despachada) AS cantidad_despachada
FROM 
	smn_inventario.smn_despacho_detalle
WHERE 
	smn_despacho_id=${fld:smn_despacho_id}