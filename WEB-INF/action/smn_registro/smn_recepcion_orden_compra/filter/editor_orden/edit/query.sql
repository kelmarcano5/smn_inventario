select	
	*
from 
	smn_inventario.smn_movimiento_cabecera AS mca
	INNER JOIN 
	smn_compras.smn_orden_compra_cabecera AS occ ON mca.smn_orden_compra_rf = occ.smn_orden_compra_cabecera_id
where 
	smn_movimiento_cabecera_id = ${fld:id}

/*select 
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_refused}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' then '${lbl:b_generated}'
		when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RB' then 'Recibido'
	end as mca_estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as mca_estatus_financiero,
	smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as smn_almacen_rf_selec,
	smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as smn_modulo_rf,
	smn_base.smn_documentos_generales.dcg_codigo||'-'||smn_base.smn_documentos_generales.dcg_descripcion as smn_documento_origen_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen,
	smn_inventario.smn_documento.doc_codigo ||' - '|| smn_inventario.smn_documento.doc_nombre as smn_documento_id,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro,
	smn_base.smn_auxiliar.aux_descripcion as smn_proveedor_rf,
	smn_compras.smn_orden_compra_cabecera.occ_orden_compra_numero as smn_orden_compra_rf,
	smn_inventario.smn_movimiento_cabecera.mca_recibo,
	
	case
	when smn_inventario.smn_movimiento_cabecera.smn_tipo_documento_pago_id='NE' then '${lbl:b_entry_note}'
	when smn_inventario.smn_movimiento_cabecera.smn_tipo_documento_pago_id='DP' then '${lbl:b_payment_document}'
	
	end as smn_tipo_documento_pago_id
from
	smn_inventario.smn_movimiento_cabecera
	INNER JOIN smn_base.smn_entidades ON smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	left outer JOIN smn_base.smn_sucursales ON smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf
	LEFT OUTER JOIN  smn_base.smn_almacen ON smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	LEFT OUTER JOIN smn_base.smn_modulos ON smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
	left outer join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
	LEFT OUTER JOIN smn_base.smn_documentos_generales ON smn_base.smn_documentos_generales.smn_documentos_generales_id =  smn_inventario.smn_documento.smn_documento_general_rf
	left outer join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
	left outer join smn_compras.smn_proveedor on smn_compras.smn_proveedor.smn_proveedor_id=smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf
	left outer join smn_base.smn_auxiliar on smn_base.smn_auxiliar.smn_auxiliar_id=smn_compras.smn_proveedor.smn_auxiliar_rf
	left outer join smn_compras.smn_orden_compra_cabecera on smn_compras.smn_orden_compra_cabecera.smn_orden_compra_cabecera_id=smn_inventario.smn_movimiento_cabecera.smn_orden_compra_rf
where
smn_movimiento_cabecera_id = ${fld:id}*/