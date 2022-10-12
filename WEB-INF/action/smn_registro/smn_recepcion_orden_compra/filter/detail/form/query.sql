select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='CO' then '${lbl:b_posted}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RC' then '${lbl:b_received}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PR' then '${lbl:b_received_partial}'
	end as estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as estatus_financiero,
	case
	when smn_inventario.smn_documento.doc_tipo_documento_pago='NE' then '${lbl:b_entry_note}'
	when smn_inventario.smn_documento.doc_tipo_documento_pago='DP' then '${lbl:b_payment_document}'
	end as doc_tipo_documento_pago,
	smn_base.smn_entidades.ent_descripcion_corta as entidad,
	smn_base.smn_sucursales.suc_nombre AS sucursal,
	smn_base.smn_almacen.alm_nombre as almacen,
	smn_base.smn_modulos.mod_nombre as modulo,
	smn_base.smn_documentos_generales.dcg_descripcion as documento_origen,
	smn_inventario.smn_documento.doc_nombre AS documento,
	smn_inventario.smn_tipo_documento.tdc_nombre AS smn_tipo_documento_id,
	smn_base.smn_auxiliar.aux_codigo||'-'||smn_base.smn_auxiliar.aux_descripcion AS proveedor,
	smn_compras.smn_orden_compra_cabecera.occ_orden_compra_numero AS orden_compra,
	smn_inventario.smn_movimiento_cabecera.*
from
	smn_inventario.smn_movimiento_cabecera
INNER JOIN smn_base.smn_entidades ON (smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf)
LEFT OUTER JOIN smn_base.smn_sucursales ON (smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf)
LEFT OUTER JOIN  smn_base.smn_almacen ON (smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf)
LEFT OUTER JOIN smn_base.smn_modulos ON (smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf)
LEFT OUTER JOIN smn_base.smn_documentos_generales ON (smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf)
LEFT OUTER JOIN smn_inventario.smn_documento ON (smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id)
LEFT OUTER JOIN smn_inventario.smn_tipo_documento ON (smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id)
LEFT OUTER JOIN smn_compras.smn_proveedor ON smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf = smn_compras.smn_proveedor.smn_proveedor_id
LEFT OUTER JOIN smn_base.smn_auxiliar ON smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf 
LEFT OUTER JOIN smn_compras.smn_orden_compra_cabecera ON smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id=smn_inventario.smn_movimiento_cabecera.smn_orden_compra_rf
where
	smn_movimiento_cabecera_id = ${fld:id}
