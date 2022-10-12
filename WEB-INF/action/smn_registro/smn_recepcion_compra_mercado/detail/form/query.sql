select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_registry}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' then '${lbl:b_generated}'
	end as estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as estatus_financiero,
	case
	when smn_inventario.smn_documento.doc_tipo_documento_pago='NE' then '${lbl:b_entry_note}'
	when smn_inventario.smn_documento.doc_tipo_documento_pago='DP' then '${lbl:b_payment_document}'
	end as tipo_documento_pago,
	smn_base.smn_entidades.ent_descripcion_corta AS entidad,
	smn_base.smn_sucursales.suc_nombre AS sucursal,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as almacen,
	smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as modulo,
	smn_base.smn_documentos_generales.dcg_codigo||'-'||smn_base.smn_documentos_generales.dcg_descripcion as documento_origen,
	smn_inventario.smn_documento.doc_codigo||'-'||smn_inventario.smn_documento.doc_nombre AS documento,
	auxiliar_proveedor.aux_codigo||'-'||auxiliar_proveedor.aux_descripcion AS proveedor,
	auxiliar_pagador.aux_descripcion AS pagador,
	smn_base.smn_tasas_de_cambio.tca_tasa_cambio AS tasa,
	smn_base.smn_monedas.mon_codigo||'-'||smn_base.smn_monedas.mon_nombre AS moneda,
	smn_inventario.smn_movimiento_cabecera.*
from
	smn_inventario.smn_movimiento_cabecera
INNER JOIN smn_base.smn_entidades ON (smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf)
left outer JOIN smn_base.smn_sucursales ON (smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf)
LEFT OUTER JOIN  smn_base.smn_almacen ON (smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf)
LEFT OUTER JOIN smn_base.smn_modulos ON (smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf)
LEFT OUTER JOIN smn_base.smn_documentos_generales ON (smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf)
LEFT OUTER JOIN smn_inventario.smn_documento ON (smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id)
LEFT OUTER JOIN smn_compras.smn_proveedor ON smn_inventario.smn_movimiento_cabecera.smn_proveedor_rf = smn_compras.smn_proveedor.smn_proveedor_id
LEFT OUTER JOIN smn_base.smn_auxiliar AS auxiliar_proveedor ON auxiliar_proveedor.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf
LEFT OUTER JOIN smn_base.smn_auxiliar AS auxiliar_pagador ON auxiliar_pagador.smn_auxiliar_id = smn_inventario.smn_movimiento_cabecera.smn_auxiliar_rf
LEFT OUTER JOIN smn_base.smn_tasas_de_cambio ON smn_base.smn_tasas_de_cambio.smn_tasas_de_cambio_id = smn_inventario.smn_movimiento_cabecera.smn_tasa_rf
LEFT OUTER JOIN smn_base.smn_monedas ON smn_base.smn_monedas.smn_monedas_id = smn_inventario.smn_movimiento_cabecera.smn_moneda_rf
where
	smn_movimiento_cabecera_id = ${fld:id}