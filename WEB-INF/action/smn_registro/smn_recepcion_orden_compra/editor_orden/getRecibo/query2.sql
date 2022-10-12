SELECT
	crp_numero_documento
FROM
	smn_inventario.smn_control_recepcion_parcial
WHERE
	crp_numero_documento = ${fld:mca_recibo}