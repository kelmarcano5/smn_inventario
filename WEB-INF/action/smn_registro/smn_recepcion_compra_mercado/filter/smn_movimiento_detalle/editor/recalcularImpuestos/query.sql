select 
	smn_base.smn_codigos_impuestos.imp_monto_sustraendo,
	smn_base.smn_codigos_impuestos.imp_porcentaje_calculo,
	smn_base.smn_codigos_impuestos.imp_porcentaje_base,
	smn_base.smn_codigos_impuestos.imp_tipo_codigo
FROM
	smn_base.smn_codigos_impuestos
where
	smn_codigos_impuestos_id=${fld:smn_codigos_impuestos_id}