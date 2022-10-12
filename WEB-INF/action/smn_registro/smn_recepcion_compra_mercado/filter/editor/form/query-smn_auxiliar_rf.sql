SELECT
	smn_base.smn_auxiliar.smn_auxiliar_id AS id, 
	smn_base.smn_auxiliar.aux_codigo || ' - ' || smn_base.smn_auxiliar.aux_descripcion AS item 
FROM 
	smn_base.smn_auxiliar 
	INNER JOIN
	smn_base.smn_clase_auxiliar ON smn_base.smn_auxiliar.smn_clase_auxiliar_rf = smn_base.smn_clase_auxiliar.smn_clase_auxiliar_id
WHERE
	smn_base.smn_clase_auxiliar.cla_codigo = 'EM'
ORDER BY 
	smn_base.smn_auxiliar.aux_descripcion