select
	case
	when smn_inventario.smn_caracteristica_item.cit_tipo_costo='FI' then '${lbl:b_fijo}'
	when smn_inventario.smn_caracteristica_item.cit_tipo_costo='PR' then '${lbl:b_promedio}'
	when smn_inventario.smn_caracteristica_item.cit_tipo_costo='UL' then '${lbl:b_ultimo}'
	end as cit_tipo_costo,
	case
	when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='FI' then '${lbl:b_fifo}'
	when smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='LI' then '{${lbl:lifo}'
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
select
	case
	when smn_inventario.smn_caracteristica_item.cit_origen_producto='NA' then '${lbl:b_nacional}'
	when smn_inventario.smn_caracteristica_item.cit_origen_producto='{IM' then '${lbl:b_importado}'
	end as cit_origen_producto,
select
select
select
	case
	when smn_inventario.smn_caracteristica_item.cit_estatus='AC' then '${lbl:b_account_type_active}'
	when smn_inventario.smn_caracteristica_item.cit_estatus='IN' then '${lbl:b_inactive}'
	when smn_inventario.smn_caracteristica_item.cit_estatus='CU' then '${lbl:b_cuarentena}'
	end as cit_estatus,
select
	case
	when smn_inventario.smn_caracteristica_item.cit_es_medicamento='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_caracteristica_item.cit_es_medicamento='NO' then '${lbl:b_not}'
	end as cit_es_medicamento,
select
select
select
select
select
select
	case
	when smn_inventario.smn_caracteristica_item.cit_req_control_lote='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_caracteristica_item.cit_req_control_lote='NO' then '${lbl:b_not}'
	end as cit_req_control_lote,
	case
	when smn_inventario.smn_caracteristica_item.cit_req_control_vencimiento='SI' then '${lbl:b_yes}'
	when smn_inventario.smn_caracteristica_item.cit_req_control_vencimiento='NO' then '${lbl:b_not}'
	end as cit_req_control_vencimiento,
	smn_inventario.smn_caracteristica_item.cit_dias_ant_vencimiento,
	smn_inventario.smn_caracteristica_item.cit_tipo_costo,
	smn_inventario.smn_caracteristica_item.cit_valoracion_inventario,
	smn_inventario.smn_caracteristica_item.cit_metodo_despacho,
	smn_inventario.smn_caracteristica_item.cit_cant_minima,
	smn_inventario.smn_caracteristica_item.cit_cant_maxima,
	smn_inventario.smn_caracteristica_item.cit_punto_reorden,
	smn_inventario.smn_caracteristica_item.cit_cant_seguridad,
	smn_inventario.smn_caracteristica_item.cit_es_reusable,
	smn_inventario.smn_caracteristica_item.cit_reuso_apertura,
	smn_inventario.smn_caracteristica_item.cit_peso,
	smn_inventario.smn_caracteristica_item.cit_cant_reuso,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_peso_rf,
	smn_inventario.smn_caracteristica_item.cit_origen_producto,
	smn_inventario.smn_caracteristica_item.cit_alto,
	smn_inventario.smn_caracteristica_item.cit_descripcion_compra,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_alto_rf,
	smn_inventario.smn_caracteristica_item.cit_codigo_arancel,
	smn_inventario.smn_caracteristica_item.cit_ancho,
	smn_inventario.smn_caracteristica_item.cit_dias_entrega,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_ancho_rf,
	smn_inventario.smn_caracteristica_item.smn_centro_costo_rf,
	smn_inventario.smn_caracteristica_item.cit_profundidad,
	smn_inventario.smn_caracteristica_item.cit_estatus,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_profundidad_rf,
	smn_inventario.smn_caracteristica_item.cit_vigencia,
	smn_inventario.smn_caracteristica_item.cit_es_medicamento,
	smn_inventario.smn_caracteristica_item.smn_principio_activo_rf,
	smn_inventario.smn_caracteristica_item.smn_item_rf,
	smn_inventario.smn_caracteristica_item.cit_codigo_barra,
	smn_inventario.smn_caracteristica_item.cit_codigo_qr,
	smn_inventario.smn_caracteristica_item.cit_codigo_alterno,
	smn_inventario.smn_caracteristica_item.cit_descripcion_tecnica,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_base_rf,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_compra_rf,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_venta_rf,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_almacenamiento_rf,
	smn_inventario.smn_caracteristica_item.smn_unidad_medida_despacho_rf,
	smn_inventario.smn_caracteristica_item.cit_idioma,
	smn_inventario.smn_caracteristica_item.cit_usuario,
	smn_inventario.smn_caracteristica_item.cit_req_control_lote,
	smn_inventario.smn_caracteristica_item.cit_fecha_registro,
	smn_inventario.smn_caracteristica_item.cit_req_control_vencimiento,
	smn_inventario.smn_caracteristica_item.cit_hora,
		smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id
from
	smn_inventario.smn_caracteristica_item
where
	smn_caracteristica_item_id = ${fld:id}
