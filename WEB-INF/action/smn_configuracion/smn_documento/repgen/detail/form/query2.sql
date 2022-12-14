select
		smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_documento_general_rf,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_afec_documento,
	smn_inventario.smn_documento.doc_usa_moneda_alterna,
	smn_inventario.smn_documento.doc_documento_manual,
	smn_inventario.smn_documento.dos_multiples_pagador,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documentos_fact_id,
	smn_inventario.smn_documento.smn_documentos_generales_rf,
	smn_inventario.smn_documento.dcf_codigo,
	smn_inventario.smn_documento.dcf_descripcion,
	smn_inventario.smn_documento.dcf_tipo_movimiento,
	smn_inventario.smn_documento.dcf_req_vigencia,
	smn_inventario.smn_documento.dcf_req_fecha_vencimiento,
	smn_inventario.smn_documento.dcf_gen_orden_despacho,
	smn_inventario.smn_documento.dcf_req_servicios,
	smn_inventario.smn_documento.dcf_req_vendedor,
	smn_inventario.smn_documento.dcf_req_tiempo_entrega,
	smn_inventario.smn_documento.dcf_usa_bonificaciones,
	smn_inventario.smn_documento.dcf_usa_descuentos,
	smn_inventario.smn_documento.dcf_moneda_alterna,
	smn_inventario.smn_documento.dcf_selecciona_precio,
	smn_inventario.smn_documento.dcf_usa_anexo,
	smn_inventario.smn_documento.dcf_vigencia,
	smn_inventario.smn_documento.dcf_estatus,
	smn_inventario.smn_documento.dcf_fecha_registro,
	smn_inventario.smn_documento.smn_modulo_rf,
	smn_inventario.smn_documento.smn_entidades_rf,
	smn_inventario.smn_documento.smn_sucursales_rf,
	smn_inventario.smn_documento.smn_documentos_generales_rf,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_numero_documento,
	smn_inventario.smn_documento.smn_clase_auxiliar_rf,
	smn_inventario.smn_documento.smn_auxiliar_rf,
	smn_inventario.smn_documento.smn_clase_auxiliar_ord_rf,
	smn_inventario.smn_documento.smn_auxiliar_ord_rf,
	smn_inventario.smn_documento.doc_orden_compra_rf,
	smn_inventario.smn_documento.smn_centro_costo_rf,
	smn_inventario.smn_documento.smn_proyecto_rf,
	smn_inventario.smn_documento.doc_fecha_doc,
	smn_inventario.smn_documento.doc_fecha_rec,
	smn_inventario.smn_documento.doc_fecha_vcto,
	smn_inventario.smn_documento.doc_planilla_importacion,
	smn_inventario.smn_documento.doc_monto_ml,
	smn_inventario.smn_documento.doc_monto_me,
	smn_inventario.smn_documento.doc_tasa_cambio,
	smn_inventario.smn_documento.smn_documentos_generales_rf_afecta,
	smn_inventario.smn_documento.doc_numero_doc_afecta,
	smn_inventario.smn_documento.doc_numero_control_doc_afect,
	smn_inventario.smn_documento.doc_fecha_doc_afecta,
	smn_inventario.smn_documento.smn_codigos_impuestos_rf,
	smn_inventario.smn_documento.doc_numero_control_fiscal_inicial,
	smn_inventario.smn_documento.doc_numero_control_fiscal_ultimo,
	smn_inventario.smn_documento.doc_numero_control1_inicial,
	smn_inventario.smn_documento.doc_numero_control1_ultimo,
	smn_inventario.smn_documento.doc_numero_control2_inicial,
	smn_inventario.smn_documento.doc_numero_control2_ultimo,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_ano_comprobante,
	smn_inventario.smn_documento.doc_periodos_detalles_rf,
	smn_inventario.smn_documento.smn_tipo_comprobante_id,
	smn_inventario.smn_documento.doc_num_comprobante,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.doc_fecha_comprobante,
	smn_inventario.smn_documento.doc_numero_control,
	smn_inventario.smn_documento.smn_elemento_rf,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.smn_documento_general_rf,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_afec_documento,
	smn_inventario.smn_documento.doc_usa_moneda_alterna,
	smn_inventario.smn_documento.doc_multiples_pagadores,
	smn_inventario.smn_documento.smn_tipo_servicio_rf,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documento_impuesto_id,
	smn_inventario.smn_documento.dto_codigo,
	smn_inventario.smn_documento.dto_nombre,
	smn_inventario.smn_documento.smn_tarifa_id,
	smn_inventario.smn_documento.dto_clase_auxiliar,
	smn_inventario.smn_documento.dto_auxiliar,
	smn_inventario.smn_documento.dto_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documento_impuesto_id,
	smn_inventario.smn_documento.dto_codigo,
	smn_inventario.smn_documento.dto_nombre,
	smn_inventario.smn_documento.smn_tarifa_id,
	smn_inventario.smn_documento.dto_clase_auxiliar,
	smn_inventario.smn_documento.dto_auxiliar,
	smn_inventario.smn_documento.dto_fecha_registro,
	smn_inventario.smn_documento.smn_documentos_generales_rf,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.smn_modulo_origen_rf,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_ver_recepcion_item,
	smn_inventario.smn_documento.doc_ver_recepcion_servicio,
	smn_inventario.smn_documento.doc_ver_orden_compra,
	smn_inventario.smn_documento.doc_ver_control_documento,
	smn_inventario.smn_documento.doc_porcentaje,
	smn_inventario.smn_documento.doc_monto_fijo,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.smn_documento_general_rf,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_nombre,
	smn_inventario.smn_documento.doc_secuencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.doc_maneja_moneda_alt,
	smn_inventario.smn_documento.smn_persona_id,
	smn_inventario.smn_documento.doc_categoria,
	smn_inventario.smn_documento.doc_tipo_documento,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_fecha_emision,
	smn_inventario.smn_documento.doc_fecha_venc,
	smn_inventario.smn_documento.doc_filename,
	smn_inventario.smn_documento.doc_content_type,
	smn_inventario.smn_documento.doc_image_size,
	smn_inventario.smn_documento.doc_image_data,
	smn_inventario.smn_documento.doc_verificado,
	smn_inventario.smn_documento.doc_observ_verif,
	smn_inventario.smn_documento.doc_aprobado,
	smn_inventario.smn_documento.doc_observ_aprob,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documentos_fact_id,
	smn_inventario.smn_documento.smn_documentos_generales_rf,
	smn_inventario.smn_documento.dcf_codigo,
	smn_inventario.smn_documento.dcf_descripcion,
	smn_inventario.smn_documento.dcf_tipo_movimiento,
	smn_inventario.smn_documento.dcf_req_vigencia,
	smn_inventario.smn_documento.dcf_req_fecha_vencimiento,
	smn_inventario.smn_documento.dcf_gen_orden_despacho,
	smn_inventario.smn_documento.dcf_req_servicios,
	smn_inventario.smn_documento.dcf_req_vendedor,
	smn_inventario.smn_documento.dcf_req_tiempo_entrega,
	smn_inventario.smn_documento.dcf_usa_bonificaciones,
	smn_inventario.smn_documento.dcf_usa_descuentos,
	smn_inventario.smn_documento.dcf_moneda_alterna,
	smn_inventario.smn_documento.dcf_selecciona_precio,
	smn_inventario.smn_documento.dcf_usa_anexo,
	smn_inventario.smn_documento.dcf_vigencia,
	smn_inventario.smn_documento.dcf_estatus,
	smn_inventario.smn_documento.dcf_fecha_registro,
	smn_inventario.smn_documento.smn_documento_general_rf,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_descripcion,
	smn_inventario.smn_documento.doc_afec_documento,
	smn_inventario.smn_documento.doc_usa_moneda_alterna,
	smn_inventario.smn_documento.doc_multiples_pagador,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_fecha_registro,
	smn_inventario.smn_documento.smn_tipo_documento_id,
	smn_inventario.smn_documento.doc_codigo,
	smn_inventario.smn_documento.doc_nombre,
	smn_inventario.smn_documento.smn_documento_general_rf,
	smn_inventario.smn_documento.smn_modulo_origen_rf,
	smn_inventario.smn_documento.doc_despacho_int_consumo,
	smn_inventario.smn_documento.doc_despacho_int_transferencia,
	smn_inventario.smn_documento.doc_despacho_venta,
	smn_inventario.smn_documento.doc_secuencia,
	smn_inventario.smn_documento.doc_vigencia,
	smn_inventario.smn_documento.doc_estatus,
	smn_inventario.smn_documento.doc_tipo_movimiento,
	smn_inventario.smn_documento.doc_tipo_documento_pago,
	smn_inventario.smn_documento.doc_fecha_registro
from
	smn_inventario.smn_documento 
where
	smn_inventario.smn_documento.smn_documento_id = ${fld:id}
