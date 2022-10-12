select
		smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf
from
		smn_inventario.smn_rel_item_cod_impuesto
where
		smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf = ${fld:smn_codigo_impuesto_rf}
