SELECT 
	smn_item_id as id,
	itm_codigo||'-'||itm_nombre as item 
FROM
	smn_base.smn_nivel_estructura
	INNER JOIN
	smn_base.smn_item ON smn_base.smn_nivel_estructura.smn_nivel_estructura_id = smn_base.smn_item.smn_nivel_estructura_id
WHERE 
	smn_base.smn_nivel_estructura.smn_estructura_codigo_id = ${fld:id}
	AND
	smn_base.smn_nivel_estructura.nes_tipo IN ('IT')