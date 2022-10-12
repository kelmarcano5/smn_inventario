select
		smn_inventario.smn_conteo.con_descripcion
from
		smn_inventario.smn_conteo
where
		upper(smn_inventario.smn_conteo.con_descripcion) = upper(${fld:con_descripcion})
