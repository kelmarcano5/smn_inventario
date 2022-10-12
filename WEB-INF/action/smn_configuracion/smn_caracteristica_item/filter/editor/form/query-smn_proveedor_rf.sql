SELECT
	smn_proveedor_id AS id,
	aux_codigo ||'-'|| aux_descripcion AS item
FROM
	smn_compras.smn_proveedor
INNER JOIN
	smn_base.smn_auxiliar ON smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf
