select
		smn_inventario.smn_principio_activo.pac_codigo
from
		smn_inventario.smn_principio_activo
where
		(smn_inventario.smn_principio_activo.pac_codigo) = (${fld:pac_codigo})
