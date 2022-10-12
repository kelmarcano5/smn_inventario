SELECT
	smn_compras.smn_proveedor.smn_proveedor_id as id,
	smn_base.smn_auxiliar.aux_codigo || ' - ' || smn_base.smn_auxiliar.aux_descripcion as item
FROM
	smn_base.smn_auxiliar
INNER JOIN
	smn_compras.smn_proveedor
ON
	smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf
ORDER BY
	smn_base.smn_auxiliar.aux_descripcion