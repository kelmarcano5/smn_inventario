select
		smn_inventario.smn_transporte.smn_transporte_id,
	case
	when smn_inventario.smn_transporte.tra_tipo_transporte='IN' then '${lbl:b_interior}'
	when smn_inventario.smn_transporte.tra_tipo_transporte='EX' then '${lbl:b_external}'
	end as tra_tipo_transporte,
	case
	when smn_inventario.smn_transporte.tra_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_transporte.tra_estatus='IN' then '${lbl:b_inactive}'
	end as tra_estatus,
	smn_inventario.smn_transporte.tra_codigo,
	smn_inventario.smn_transporte.tra_descripcion_transporte,
	smn_inventario.smn_transporte.tra_tipo_transporte,
	smn_inventario.smn_transporte.smn_proveedor_rf,
	smn_inventario.smn_transporte.tra_estatus,
	smn_inventario.smn_transporte.tra_fecha_registro
	
from
	smn_inventario.smn_transporte
