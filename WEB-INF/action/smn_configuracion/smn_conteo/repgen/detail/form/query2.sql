select
		smn_inventario.smn_conteo.con_codigo,
	smn_inventario.smn_conteo.con_descripcion,
	smn_inventario.smn_conteo.smn_almacen_rf,
	smn_inventario.smn_conteo.smn_ubicacion_id,
	smn_inventario.smn_conteo.smn_rol_1_id,
	smn_inventario.smn_conteo.smn_rol_2_id,
	smn_inventario.smn_conteo.con_estatus,
	smn_inventario.smn_conteo.con_fecha_vigencia,
	smn_inventario.smn_conteo.con_fecha_registro
from
	smn_inventario.smn_conteo 
where
	smn_inventario.smn_conteo.smn_conteo_id = ${fld:id}
