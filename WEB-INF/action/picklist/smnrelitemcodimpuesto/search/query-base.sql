select 
	smn_inventario.smn_rel_item_cod_impuesto.smn_rel_item_cod_impuesto_id as id,
	smn_inventario.smn_rel_item_cod_impuesto.smn_caracteristica_item_id,
	smn_base.smn_codigos_impuestos.imp_codigo ||' - '|| smn_base.smn_codigos_impuestos.imp_descripcion as item
from 
	smn_inventario.smn_rel_item_cod_impuesto
	inner join smn_base.smn_codigos_impuestos on smn_base.smn_codigos_impuestos.smn_codigos_impuestos_id = smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf
where
	smn_rel_item_cod_impuesto_id is not null
	${filter}