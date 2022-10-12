select
		smn_inventario.smn_transporte.tra_descripcion_transporte
from
		smn_inventario.smn_transporte
where
		upper(smn_inventario.smn_transporte.tra_descripcion_transporte) = upper(${fld:tra_descripcion_transporte})
