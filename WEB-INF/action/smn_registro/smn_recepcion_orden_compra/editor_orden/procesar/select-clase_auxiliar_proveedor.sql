SELECT
	smn_compras.smn_proveedor.smn_clase_auxiliar_rf,
	smn_compras.smn_proveedor.smn_auxiliar_rf,
	smn_base.smn_auxiliar.aux_rif
FROM
	smn_compras.smn_proveedor
	INNER JOIN
	smn_base.smn_auxiliar ON smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf
WHERE
	smn_proveedor_id = ${fld:smn_proveedor_rf}