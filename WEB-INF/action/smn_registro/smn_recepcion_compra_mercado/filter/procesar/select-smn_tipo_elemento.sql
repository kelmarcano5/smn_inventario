SELECT
	smn_tipo_elemento_id
FROM
	smn_cont_financiera.smn_tipo_elemento
WHERE
	smn_diccionario_rf = ${fld:smn_diccionario_id}
AND
	smn_modulo_rf = ${fld:smn_modulos_id}