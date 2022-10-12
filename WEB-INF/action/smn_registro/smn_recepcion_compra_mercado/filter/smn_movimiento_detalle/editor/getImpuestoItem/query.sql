SELECT
	smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id AS id,
	smn_base.smn_codigos_impuestos.imp_codigo||' - '||smn_base.smn_codigos_impuestos.imp_descripcion AS item,
	smn_base.smn_codigos_impuestos.imp_tipo_codigo,
	smn_base.smn_codigos_impuestos.imp_porcentaje_base,
	smn_base.smn_codigos_impuestos.imp_porcentaje_calculo,
	smn_base.smn_codigos_impuestos.imp_monto_sustraendo
FROM 
	smn_base.smn_rel_item_cod_impuesto 
	INNER JOIN 
	smn_base.smn_codigos_impuestos ON smn_base.smn_rel_item_cod_impuesto.smn_codigos_impuestos_id = smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id
WHERE 
	smn_base.smn_rel_item_cod_impuesto.smn_item_id = ${fld:smn_item_rf}