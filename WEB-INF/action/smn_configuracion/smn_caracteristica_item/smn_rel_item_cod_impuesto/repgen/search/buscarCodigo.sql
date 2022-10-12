select
		smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf
from
		smn_inventario.smn_rel_item_cod_impuesto
where
		upper(smn_inventario.smn_rel_item_cod_impuesto.smn_codigo_impuesto_rf) = upper(${fld:smn_codigo_impuesto_rf})
