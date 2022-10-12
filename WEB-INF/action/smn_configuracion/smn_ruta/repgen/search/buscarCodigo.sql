select
		smn_inventario.smn_ruta.rut_codigo
from
		smn_inventario.smn_ruta
where
		upper(smn_inventario.smn_ruta.rut_codigo) = upper(${fld:rut_codigo})
