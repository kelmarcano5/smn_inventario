select
	smn_inventario.smn_tipo_documento.smn_tipo_documento_id,
	smn_inventario.smn_tipo_documento.tdc_codigo as tdc_codigo_pl0,
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
	smn_inventario.smn_documento.*
from
	smn_inventario.smn_tipo_documento,
	smn_inventario.smn_documento
where
	smn_inventario.smn_tipo_documento.smn_tipo_documento_id=smn_inventario.smn_documento.smn_tipo_documento_id
	and
	smn_documento_id = ${fld:id}
