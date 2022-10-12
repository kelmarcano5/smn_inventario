select 
	smn_base.smn_codigos_impuestos.imp_monto_sustraendo as sustraendo,
	smn_base.smn_codigos_impuestos.imp_porcentaje_calculo as porcentaje,
	smn_base.smn_codigos_impuestos.imp_porcentaje_base as porcbase,
	smn_base.smn_codigos_impuestos.imp_tipo_codigo as tipocod
FROM
	smn_base.smn_codigos_impuestos
where
	smn_codigos_impuestos_id=${fld:id}