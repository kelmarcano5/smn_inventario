select
		smn_inventario.smn_ruta.smn_ruta_id,
	case
	when smn_inventario.smn_ruta.rut_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_ruta.rut_estatus='IN' then '${lbl:b_inactive}'
	end as rut_estatus,
	smn_inventario.smn_ruta.rut_codigo,
	smn_inventario.smn_ruta.rut_nombre,
	smn_inventario.smn_ruta.smn_zona_rf,
	smn_inventario.smn_ruta.rut_posicion_ruta,
	smn_inventario.smn_ruta.rut_estatus,
	smn_inventario.smn_ruta.rut_vigencia,
	smn_inventario.smn_ruta.rut_fecha_registro
	
from
	smn_inventario.smn_ruta
