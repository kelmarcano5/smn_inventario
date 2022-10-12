SELECT
	smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id AS id, 
	smn_base.smn_codigos_impuestos.imp_codigo|| ' - ' ||smn_base.smn_codigos_impuestos.imp_descripcion AS item 
FROM
	smn_base.smn_codigos_impuestos