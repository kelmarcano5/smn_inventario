SELECT 
	smn_item_id as id,
	itm_codigo||'-'||itm_nombre as item 
FROM 
	smn_base.smn_item 