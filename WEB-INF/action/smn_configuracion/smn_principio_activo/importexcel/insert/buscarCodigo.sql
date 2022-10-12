select
		smn_inventario.smn_principio_activo.pac_codigo
from
		smn_inventario.smn_principio_activo
where
		upper(smn_inventario.smn_principio_activo.pac_codigo) = upper(${fld:pac_codigo})
