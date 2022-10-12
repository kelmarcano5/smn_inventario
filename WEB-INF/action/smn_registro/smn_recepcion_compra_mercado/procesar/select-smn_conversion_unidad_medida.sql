SELECT
	cou_factor_conversion,
	cou_signo_conversion
FROM
	smn_base.smn_conversion_unidad_medida
WHERE
	smn_unidad_medida_origen_rf=${fld:smn_unidad_medida_rf}