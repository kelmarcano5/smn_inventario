select
	smn_inventario.smn_ubicacion_detalle.*
from
	smn_inventario.smn_ubicacion_detalle
where
	smn_ubicacion_detalle_id = ${fld:id}
