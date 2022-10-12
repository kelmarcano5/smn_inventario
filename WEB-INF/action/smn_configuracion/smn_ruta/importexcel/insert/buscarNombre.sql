select
		smn_inventario.smn_ruta.rut_nombre
from
		smn_inventario.smn_ruta
where
		upper(smn_inventario.smn_ruta.rut_nombre) = upper(${fld:rut_nombre})
