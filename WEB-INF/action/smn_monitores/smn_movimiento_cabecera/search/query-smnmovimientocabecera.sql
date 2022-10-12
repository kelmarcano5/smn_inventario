select 
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_inventario.smn_movimiento_detalle.mde_lote as lote,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_solicitada,
	smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_sucursales.suc_nombre as smn_sucursal_rf,
	smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_inventario.smn_tipo_documento.tdc_nombre as smn_documento_origen_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen,
	smn_inventario.smn_movimiento_cabecera.smn_documento_id,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_recibida,
	smn_base.smn_item.itm_codigo ||' - '||smn_base.smn_item.itm_nombre as item,
	case
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_refused}'
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' then '${lbl:b_generated}'
	end as mca_estatus_proceso,
	case
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
			when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as mca_estatus_financiero,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro
from
	smn_inventario.smn_movimiento_cabecera
	inner join smn_inventario.smn_movimiento_detalle on smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = 	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
	INNER JOIN smn_base.smn_entidades ON smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf
	left outer JOIN smn_base.smn_sucursales ON smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf
	LEFT OUTER JOIN  smn_base.smn_almacen ON smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf
	LEFT OUTER JOIN smn_base.smn_modulos ON smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
	LEFT OUTER JOIN smn_base.smn_documentos_generales ON smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf
	inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
	inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
where
	smn_inventario.smn_movimiento_cabecera.smn_entidad_rf=${fld:entidad} and 
	smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf=${fld:sucursal} and 
	smn_inventario.smn_movimiento_cabecera.smn_almacen_rf=${fld:almacen}

order by
		smn_base.smn_item.itm_codigo, smn_inventario.smn_movimiento_cabecera.mca_fecha_registro