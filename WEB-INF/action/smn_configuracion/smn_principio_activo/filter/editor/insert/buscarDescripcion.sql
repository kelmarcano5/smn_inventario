select
		smn_inventario.smn_principio_activo.pac_descripcion_completa
from
		smn_inventario.smn_principio_activo
where
		upper(smn_inventario.smn_principio_activo.pac_descripcion_completa) = upper(${fld:pac_descripcion_completa})
