SELECT
	smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre AS smn_item_rf,
	smn_base.smn_item.itm_nombre AS cfc_nombre,
	smn_base.smn_item.smn_item_id as cfc_item_id,
	smn_inventario.smn_caracteristica_item.cit_codigo_barra,
	smn_inventario.smn_caracteristica_item.cit_codigo_qr,
	smn_inventario.smn_caracteristica_item.cit_codigo_alterno,
	smn_inventario.smn_caracteristica_item.cit_descripcion_tecnica,
	unimed1.unm_codigo ||' - '|| unimed1.unm_descripcion AS smn_unidad_medida_base_rf,
	unimed2.unm_codigo ||' - '|| unimed2.unm_descripcion AS smn_unidad_medida_compra_rf,
	unimed3.unm_codigo ||' - '|| unimed3.unm_descripcion AS smn_unidad_medida_venta_rf,
	unimed4.unm_codigo ||' - '|| unimed4.unm_descripcion AS smn_unidad_medida_almacenamiento_rf,
	unimed5.unm_codigo ||' - '|| unimed5.unm_descripcion AS smn_unidad_medida_despacho_rf,
	smn_inventario.smn_caracteristica_item.cit_peso,
	unimed6.unm_codigo ||' - '|| unimed6.unm_descripcion AS smn_unidad_medida_peso_rf,
	smn_inventario.smn_caracteristica_item.cit_alto,
	unimed7.unm_codigo ||' - '|| unimed7.unm_descripcion AS smn_unidad_medida_alto_rf,
	smn_inventario.smn_caracteristica_item.cit_ancho,
	unimed8.unm_codigo ||' - '|| unimed8.unm_descripcion AS smn_unidad_medida_ancho_rf,
	smn_inventario.smn_caracteristica_item.cit_profundidad,
	unimed9.unm_codigo ||' - '|| unimed9.unm_descripcion AS smn_unidad_medida_profundidad_rf,
	case
		when smn_inventario.smn_caracteristica_item.cit_es_medicamento='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_es_medicamento='NO' then '${lbl:b_not}'
	end as cit_es_medicamento,
	smn_inventario.smn_caracteristica_item.smn_principio_activo_rf,
	case
		when smn_inventario.smn_caracteristica_item.cit_req_control_lote='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_req_control_lote='NO' then '${lbl:b_not}'
	end as cit_req_control_lote,
	case
		when smn_inventario.smn_caracteristica_item.cit_req_control_vencimiento='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_req_control_vencimiento='NO' then '${lbl:b_not}'
	end as cit_req_control_vencimiento,
	smn_inventario.smn_caracteristica_item.cit_dias_ant_vencimiento,
	case
		when smn_inventario.smn_caracteristica_item.cit_tipo_costo='FI' then '${lbl:b_fijo}'
		when smn_inventario.smn_caracteristica_item.cit_tipo_costo='PR' then '${lbl:b_promedio}'
		when smn_inventario.smn_caracteristica_item.cit_tipo_costo='UL' then '${lbl:b_ultimo}'
		when smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA' then 'Moneda Alterna'
	end as cit_tipo_costo,
	case
		when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CP' then 'Costo Promedio'
		when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='UC' then 'Ultimo Costo'
		when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CR' then 'Costo Reposicion'
		when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CA' then 'Costo mas alto'
		when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CD' then 'Costo Promedio Ponderado'
	end as cit_valoracion_inventario,
		case
		when smn_inventario.smn_caracteristica_item.cit_metodo_despacho='FI' then '${lbl:b_fifo}'
		when smn_inventario.smn_caracteristica_item.cit_metodo_despacho='LI' then '${lbl:lifo}'
		when smn_inventario.smn_caracteristica_item.cit_metodo_despacho='VL' then '${lbl:b_vencimiento_lote}'
	end as cit_metodo_despacho,
	case
		when smn_inventario.smn_caracteristica_item.cit_es_reusable='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_es_reusable='NO' then '${lbl:b_not}'
	end as cit_es_reusable,
		case
		when smn_inventario.smn_caracteristica_item.cit_reuso_apertura='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_reuso_apertura='NO' then '${lbl:b_not}'
	end as cit_reuso_apertura,
	smn_inventario.smn_caracteristica_item.cit_cant_reuso,
		case
		when smn_inventario.smn_caracteristica_item.cit_origen_producto='NA' then '${lbl:b_nacional}'
		when smn_inventario.smn_caracteristica_item.cit_origen_producto='{IM' then '${lbl:b_importado}'
	end as cit_origen_producto,
	smn_inventario.smn_caracteristica_item.cit_descripcion_compra,
	smn_inventario.smn_caracteristica_item.cit_codigo_arancel,
	smn_inventario.smn_caracteristica_item.cit_dias_entrega,
	smn_base.smn_centro_costo.cco_codigo||' - '|| smn_base.smn_centro_costo.cco_descripcion_larga AS smn_centro_costo_rf,
		case
		when smn_inventario.smn_caracteristica_item.cit_item_compuesto='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_item_compuesto='NO' then '${lbl:b_not}'
	end as cit_item_compuesto,
	smn_inventario.smn_caracteristica_item.cit_proveedor_exclusivo,
	case
		when smn_inventario.smn_caracteristica_item.cit_almacenado='SI' then '${lbl:b_yes}'
		when smn_inventario.smn_caracteristica_item.cit_almacenado='NO' then '${lbl:b_not}'
	end as cit_almacenado,
		case
		when smn_inventario.smn_caracteristica_item.cit_estatus='AC' then '${lbl:b_account_type_active}'
		when smn_inventario.smn_caracteristica_item.cit_estatus='IN' then '${lbl:b_inactive}'
		when smn_inventario.smn_caracteristica_item.cit_estatus='CU' then '${lbl:b_cuarentena}'
	end as cit_estatus,
	smn_inventario.smn_caracteristica_item.cit_vigencia,
	smn_inventario.smn_caracteristica_item.cit_idioma,
	smn_inventario.smn_caracteristica_item.cit_usuario,
	smn_inventario.smn_caracteristica_item.cit_fecha_registro,
	smn_inventario.smn_caracteristica_item.cit_hora
FROM
	smn_inventario.smn_caracteristica_item
	INNER JOIN smn_base.smn_item ON smn_base.smn_item.smn_item_id = smn_inventario.smn_caracteristica_item.smn_item_rf
	left outer join smn_base.smn_unidad_medida AS unimed1 ON unimed1.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_base_rf
	left outer join smn_base.smn_unidad_medida AS unimed2 ON unimed2.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_compra_rf
	left outer join smn_base.smn_unidad_medida AS unimed3 ON unimed3.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_venta_rf
	left outer join smn_base.smn_unidad_medida AS unimed4 ON unimed4.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_almacenamiento_rf
	left outer join smn_base.smn_unidad_medida AS unimed5 ON unimed5.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_despacho_rf
	left outer join smn_base.smn_unidad_medida AS unimed6 ON unimed6.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_peso_rf
	left outer join smn_base.smn_unidad_medida AS unimed7 ON unimed7.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_alto_rf
	left outer join smn_base.smn_unidad_medida AS unimed8 ON unimed8.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_ancho_rf
	left outer join smn_base.smn_unidad_medida AS unimed9 ON unimed9.smn_unidad_medida_id = smn_inventario.smn_caracteristica_item.smn_unidad_medida_profundidad_rf
	left outer join smn_base.smn_centro_costo ON smn_base.smn_centro_costo.smn_centro_costo_id = smn_inventario.smn_caracteristica_item.smn_centro_costo_rf
ORDER BY 
	smn_inventario.smn_caracteristica_item.cit_fecha_registro DESC