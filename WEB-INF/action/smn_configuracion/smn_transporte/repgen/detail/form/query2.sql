select
		smn_inventario.smn_transporte.tra_codigo,
	smn_inventario.smn_transporte.tra_descripcion_transporte,
	smn_inventario.smn_transporte.tra_tipo_transporte,
	smn_inventario.smn_transporte.smn_activo_rf,
	smn_inventario.smn_transporte.smn_proveedor_rf,
	smn_inventario.smn_transporte.tra_estatus,
	smn_inventario.smn_transporte.tra_vigencia,
	smn_inventario.smn_transporte.tra_fecha_registro
from
	smn_inventario.smn_transporte 
where
	smn_inventario.smn_transporte.smn_transporte_id = ${fld:id}
