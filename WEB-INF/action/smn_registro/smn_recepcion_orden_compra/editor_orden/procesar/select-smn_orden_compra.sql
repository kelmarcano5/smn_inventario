SELECT
	smn_auxiliar_id,
	smn_clase_auxiliar_rf
FROM
	smn_compras.smn_orden_compra_cabecera AS occ
INNER JOIN
	smn_base.smn_auxiliar AS aux ON occ.smn_auxiliar_rf = aux.smn_auxiliar_id
WHERE
	smn_orden_compra_cabecera_id = ${fld:smn_orden_compra_rf}