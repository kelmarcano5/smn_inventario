select
		smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id,
	case
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='DE' then '${lbl:b_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='RE' then '${lbl:b_rejected}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='PD' then '${lbl:b_partially_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='CE' then '${lbl:b_closed}'
	end as dde_estatus_transaccion,
	smn_inventario.smn_despacho_detalle.smn_item_rf,
	smn_inventario.smn_despacho_detalle.dde_cantidad_solicitada,
	smn_inventario.smn_despacho_detalle.dde_cantidad_despachada,
	smn_inventario.smn_despacho_detalle.dde_fecha_registro
	
from
	smn_inventario.smn_despacho_detalle
