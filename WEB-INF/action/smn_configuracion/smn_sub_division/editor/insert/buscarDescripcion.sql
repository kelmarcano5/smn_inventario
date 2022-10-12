select
		smn_inventario.smn_sub_division.sdi_descripcion
from
		smn_inventario.smn_sub_division
where
		upper(smn_inventario.smn_sub_division.sdi_descripcion) = upper(${fld:sdi_descripcion})
