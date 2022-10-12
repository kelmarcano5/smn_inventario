SELECT
	smn_inventario.smn_documento.smn_documento_id,
	smn_inventario.smn_tipo_documento.tdc_codigo ||' - '|| smn_inventario.smn_tipo_documento.tdc_nombre AS smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_nombre,
	smn_base.smn_documentos_generales.dcg_codigo ||' - '|| smn_base.smn_documentos_generales.dcg_descripcion AS smn_documento_general_rf,
	smn_base.smn_modulos.mod_codigo ||' - '|| smn_base.smn_modulos.mod_nombre AS smn_modulo_origen_rf,
	case
	when smn_inventario.smn_documento.doc_despacho_int_consumo='SI' then '${lbl:b_yes} '
	when smn_inventario.smn_documento.doc_despacho_int_consumo=' NO' then '${lbl:b_no}'
	end as doc_despacho_int_consumo,
	case
	when smn_inventario.smn_documento.doc_despacho_int_transferencia='SI' then '${lbl:b_yes} '
	when smn_inventario.smn_documento.doc_despacho_int_transferencia=' NO' then '${lbl:b_no}'
	end as doc_despacho_int_transferencia,
	case
	when smn_inventario.smn_documento.doc_despacho_venta='SI' then '${lbl:b_yes} '
	when smn_inventario.smn_documento.doc_despacho_venta=' NO' then '${lbl:b_no}'
	end as doc_despacho_venta,
	smn_inventario.smn_documento.doc_secuencia,
	smn_inventario.smn_documento.doc_vigencia,
	case
	when smn_inventario.smn_documento.doc_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_documento.doc_estatus='IN' then '${lbl:b_inactive}'
	end as doc_estatus,
	case
	when smn_inventario.smn_documento.doc_tipo_movimiento='PI' then '${lbl:b_internal_order}'
	when smn_inventario.smn_documento.doc_tipo_movimiento='PE' then '${lbl:b_external_order}'
	when smn_inventario.smn_documento.doc_tipo_movimiento='TR' then '${lbl:b_transfer}'
	end as doc_tipo_movimiento,
	case
	when smn_inventario.smn_documento.doc_tipo_documento_pago='DP' then '${lbl:b_payment_document}'
	when smn_inventario.smn_documento.doc_tipo_documento_pago='NE' then '${lbl:b_entry_note}'
	end as doc_tipo_documento_pago,
	smn_inventario.smn_documento.doc_idioma,
	smn_inventario.smn_documento.doc_usuario,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.doc_hora
	
FROM
	smn_inventario.smn_documento
	INNER JOIN smn_inventario.smn_tipo_documento ON smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
	INNER JOIN smn_base.smn_documentos_generales ON smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_documento.smn_documento_general_rf
	INNER JOIN smn_base.smn_modulos ON smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_documento.smn_modulo_origen_rf
WHERE
	smn_documento_id = ${fld:id}