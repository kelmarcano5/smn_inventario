select
		smn_inventario.smn_conteo.con_codigo
from
		smn_inventario.smn_conteo
where
		upper(smn_inventario.smn_conteo.con_codigo) = upper(${fld:con_codigo})
