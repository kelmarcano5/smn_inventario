select
		smn_inventario.smn_despacho_detalle.smn_despacho_cabecera_id,
	smn_inventario.smn_despacho_detalle.smn_item_rf,
	smn_inventario.smn_despacho_detalle.dde_cantidad_solicitada,
	smn_inventario.smn_despacho_detalle.dde_cantidad_despachada,
	smn_inventario.smn_despacho_detalle.dde_costo_ml,
	smn_inventario.smn_despacho_detalle.dde_costo_ma,
	smn_inventario.smn_despacho_detalle.dde_motivo,
	smn_inventario.smn_despacho_detalle.dde_fecha_aprobacion,
	smn_inventario.smn_despacho_detalle.dde_fecha_cierre,
	smn_inventario.smn_despacho_detalle.dde_estatus_transaccion,
	smn_inventario.smn_despacho_detalle.dde_fecha_registro
from
	smn_inventario.smn_despacho_detalle 
where
	smn_inventario.smn_despacho_detalle.smn_despacho_detalle_id = ${fld:id}
