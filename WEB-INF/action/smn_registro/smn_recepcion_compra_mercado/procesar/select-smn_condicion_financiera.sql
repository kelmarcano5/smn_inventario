SELECT
	cfi_cant_dias
FROM
	smn_base.smn_condicion_financiera AS cfi
INNER JOIN
	smn_base.smn_auxiliar AS aux ON cfi.smn_condicion_financiera_id = aux.aux_condicion_financiera_rf 
WHERE
	aux.smn_auxiliar_id = ${fld:smn_auxiliar_rf}
