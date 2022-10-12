SELECT 
	smn_base.smn_centro_costo.smn_centro_costo_id AS id, 
	smn_base.smn_centro_costo.cco_codigo|| ' - ' || smn_base.smn_centro_costo.cco_descripcion_corta AS item 
FROM
	smn_base.smn_centro_costo