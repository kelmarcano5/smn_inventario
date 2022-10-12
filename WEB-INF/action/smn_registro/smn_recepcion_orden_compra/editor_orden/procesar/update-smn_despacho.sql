UPDATE smn_inventario.smn_despacho SET
	des_estatus = 'GE'
WHERE
	smn_despacho_id = ${fld:smn_despacho_id}