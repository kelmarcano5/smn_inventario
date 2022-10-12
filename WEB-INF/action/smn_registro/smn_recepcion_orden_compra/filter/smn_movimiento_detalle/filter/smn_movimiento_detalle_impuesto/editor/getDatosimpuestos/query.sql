select 
	smn_impuestos.smn_impuestos.imp_sustraendo as sustraendo,
	smn_impuestos.smn_impuestos.imp_porcentaje as porcentaje
FROM
	smn_impuestos.smn_impuestos
	INNER JOIN  smn_base.smn_codigos_impuestos ci on ci.smn_codigos_impuestos_id = smn_impuestos.smn_impuestos.smn_codigo_impuesto_rf
where
smn_codigo_impuesto_rf=${fld:id}