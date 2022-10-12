select
		smn_inventario.smn_division.div_codigo
from
		smn_inventario.smn_division
where
		upper(smn_inventario.smn_division.div_codigo) = upper(${fld:div_codigo})
