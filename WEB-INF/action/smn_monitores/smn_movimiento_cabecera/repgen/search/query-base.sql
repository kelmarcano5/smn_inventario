select  smn_inventario.smn_movimiento_cabecera.*,
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
	smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as smn_modulo_rf,
	smn_compras.smn_documentos.dcc_codigo||'-'||smn_compras.smn_documentos.dcc_nombre as smn_documento_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_compras.smn_proveedor.smn_auxiliar_rf||'-'||smn_compras.smn_proveedor.prv_usuario_id as smn_proveedor_rf,
	smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id||'-'||smn_compras.smn_orden_compra_cabecera.occ_descripcion as smn_orden_compra_rf,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_entidades.ent_codigo||'-'||smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro,
	smn_base.smn_documentos_generales.dcg_codigo||'-'||smn_base.smn_documentos_generales.dcg_descripcion as smn_documento_origen_rf,
	smn_base.smn_monedas.mon_codigo||'-'||smn_base.smn_monedas.mon_nombre as smn_moneda_rf,
	smn_base.smn_tasas_de_cambio.smn_monedas_id||'-'||smn_base.smn_tasas_de_cambio.tca_moneda_referencia as smn_tasa_rf,
	smn_inventario.smn_movimiento_detalle.*,
	smn_base.smn_item.itm_codigo||'-'||smn_base.smn_item.itm_nombre as smn_item_rf,
	smn_base.smn_centro_costo.cco_codigo||'-'||smn_base.smn_centro_costo.cco_descripcion_corta as smn_centro_costo_rf,
	smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as smn_unidad_medida_rf	
from
	smn_inventario.smn_movimiento_cabecera
	inner join smn_inventario.smn_movimiento_detalle on smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id
	left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf
	left outer join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	left outer join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
	left outer join smn_compras.smn_orden_compra_cabecera on smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_orden_compra_rf
	left outer join smn_compras.smn_documentos on smn_compras.smn_documentos.smn_documentos_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
	left outer join smn_compras.smn_proveedor on smn_compras.smn_proveedor.smn_proveedor_id = smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf
	left outer join smn_base.smn_documentos_generales on smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf
	left outer join smn_base.smn_monedas on smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_movimiento_cabecera.smn_moneda_rf
	left outer join smn_base.smn_tasas_de_cambio on smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_movimiento_cabecera.smn_tasa_rf
	left outer join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf
	left outer join smn_base.smn_centro_costo on smn_base.smn_centro_costo.smn_centro_costo_id = smn_inventario.smn_movimiento_detalle.smn_centro_costo_rf
	--left outer join smn_base.smn_tasas_de_cambio on smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_movimiento_detalle.smn_tasa_rf
	--left outer join smn_base.smn_monedas on smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_movimiento_detalle.smn_moneda_rf
	left outer join smn_base.smn_unidad_medida on smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf
where
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id is not null
	${filter}
