UPDATE smn_inventario.smn_rel_item_cod_impuesto SET
	smn_caracteristica_item_id=${fld:smn_caracteristica_item_id},
	smn_codigo_impuesto_rf=${fld:smn_codigo_impuesto_rf}

WHERE
	smn_rel_item_cod_impuesto_id=${fld:id}

