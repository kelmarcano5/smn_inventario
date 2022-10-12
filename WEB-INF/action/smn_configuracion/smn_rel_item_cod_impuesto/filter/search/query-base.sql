select	
	(select smn_inventario.smn_caracteristica_item.cit_descripcion_tecnica from  smn_inventario.smn_caracteristica_item where smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id is not null  and smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id=smn_inventario.smn_rel_item_cod_impuesto.smn_caracteristica_item) as smn_caracteristica_item_combo,
	(select smn_base.smn_codigos_impuestos.imp_codigo|| ' - ' || smn_base.smn_codigos_impuestos.imp_descripcion from  smn_base.smn_codigos_impuestos  where smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id is not null  and smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id=smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf  order by imp_descripcion ) as smn_codigo_impuesto_rf_combo,
	smn_inventario.smn_rel_item_cod_impuesto.smn_caracteristica_item,
	smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf,
	smn_inventario.smn_rel_item_cod_impuesto.smn_rel_item_cod_impuesto_id

from
	smn_inventario.smn_rel_item_cod_impuesto
where
	smn_rel_item_cod_impuesto_id is not null	
 	 	${filter}
order by 
	smn_rel_item_cod_impuesto_id