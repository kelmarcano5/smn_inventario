select
		smn_inventario.smn_sub_division.sdi_codigo
from
		smn_inventario.smn_sub_division
where
		upper(smn_inventario.smn_sub_division.sdi_codigo) = upper(${fld:sdi_codigo})
