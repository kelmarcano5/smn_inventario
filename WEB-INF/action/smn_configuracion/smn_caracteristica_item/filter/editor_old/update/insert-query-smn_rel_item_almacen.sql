INSERT INTO smn_inventario.smn_rel_item_almacen
(
	smn_rel_item_almacen_id,
	smn_caracteristica_item_id,
	smn_almacen_rf
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_rel_item_almacen},
	${fld:id},
	${fld:smn_almacen_rf}
)
