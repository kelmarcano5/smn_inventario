select
		smn_inventario.smn_transporte.tra_codigo
from
		smn_inventario.smn_transporte
where
		upper(smn_inventario.smn_transporte.tra_codigo) = upper(${fld:tra_codigo})
