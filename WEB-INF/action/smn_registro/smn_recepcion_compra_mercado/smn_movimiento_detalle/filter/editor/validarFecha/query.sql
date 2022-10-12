SELECT 
	col_fecha_vencimiento AS item
FROM
	smn_inventario.smn_control_lote
WHERE
	col_lote = ${fld:mde_lote}
	