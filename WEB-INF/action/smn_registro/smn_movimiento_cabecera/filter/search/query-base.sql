select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_refused}'
	end as mca_estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as mca_estatus_financiero,
	smn_base.smn_modulos.mod_codigo||'-'|| smn_base.smn_modulos.mod_nombre as smn_modulo_rf,
	smn_compras.smn_documentos.dcc_codigo||'-'||smn_compras.smn_documentos.dcc_nombre as smn_documento_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_compras.smn_proveedor.smn_auxiliar_rf||'-'||smn_compras.smn_proveedor.prv_usuario_id as smn_proveedor_rf,
	smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id||'-'||smn_compras.smn_orden_compra_cabecera.occ_descripcion as smn_orden_compra_rf,
	smn_base.smn_almacen.alm_codigo||'-'|| smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_entidades.ent_codigo||'-'||smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_inventario.smn_movimiento_cabecera.mca_recibo,
	smn_inventario.smn_movimiento_cabecera.mca_monto_documento_ml,
	smn_inventario.smn_movimiento_cabecera.mca_monto_documento_ma,
	smn_inventario.smn_movimiento_cabecera.mca_monto_diminucion_ml,
	smn_inventario.smn_movimiento_cabecera.mca_monto_diminucion_ma,
	smn_inventario.smn_movimiento_cabecera.mca_monto_valor_agregado_ml,
	smn_inventario.smn_movimiento_cabecera.mca_monto_valor_agregado_ma,
	smn_inventario.smn_movimiento_cabecera.mca_monto_neto_ml,
	smn_inventario.smn_movimiento_cabecera.mcc_monto_neto_ma,
	smn_inventario.smn_movimiento_cabecera.smn_moneda_rf,
	smn_inventario.smn_movimiento_cabecera.smn_tasa_rf,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_operacion,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro
	
from
	smn_inventario.smn_movimiento_cabecera
	left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf
	left outer join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	left outer join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
	left outer join smn_compras.smn_orden_compra_cabecera on smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_orden_compra_rf
	left outer join smn_compras.smn_documentos on smn_compras.smn_documentos.smn_documentos_id = smn_inventario.smn_movimiento_cabecera.smn_documento_rf
	left outer join smn_compras.smn_proveedor on smn_compras.smn_proveedor.smn_proveedor_id = smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf
where
	smn_movimiento_cabecera_id is not null
	${filter}
order by
		smn_movimiento_cabecera_id
