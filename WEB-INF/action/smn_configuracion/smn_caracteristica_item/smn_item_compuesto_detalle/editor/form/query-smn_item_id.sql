SELECT
    smn_base.smn_item.smn_item_id AS id,
    smn_base.smn_item.itm_nombre AS item
FROM
    smn_base.smn_item
order by 
	smn_base.smn_item.itm_nombre asc 