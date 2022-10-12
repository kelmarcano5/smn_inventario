select
	case
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_ubicacion_cabecera.ubc_estatus='AC' then '${lbl:b_updated}'
	end as ubc_estatus,
	smn_inventario.smn_ubicacion_cabecera.*
from
	smn_inventario.smn_ubicacion_cabecera
where
	smn_ubicacion_cabecera_id = ${fld:id}
