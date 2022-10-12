select
		smn_inventario.smn_division.div_descripcion
from
		smn_inventario.smn_division
where
		upper(smn_inventario.smn_division.div_descripcion) = upper(${fld:div_descripcion})
