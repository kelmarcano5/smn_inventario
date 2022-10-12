INSERT INTO smn_inventario.smn_rel_item_cod_impuesto
(
	smn_rel_item_cod_impuesto_id,
	smn_caracteristica_item,
	smn_codigo_impuesto_rf
)
VALUES
(
	${seq:currval@smn_inventario.seq_smn_rel_item_cod_impuesto},
	${fld:smn_caracteristica_item},
	${fld:smn_codigo_impuesto_rf}
)
