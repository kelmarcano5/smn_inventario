SELECT
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	CASE
		WHEN smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='EN' THEN '${lbl:b_entry}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='SA' THEN '${lbl:b_exit}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_tipo_movimiento='AP' THEN '${lbl:b_fit_price}'
	END AS mde_tipo_movimiento,
	CASE
		WHEN smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='SI' THEN '${lbl:b_yes}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='S' THEN '${lbl:b_yes}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='NO' THEN '${lbl:b_not}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_es_bonificacion='N' THEN '${lbl:b_not}'
	END AS mde_es_bonificacion,
	CASE
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='GE' THEN '${lbl:b_generated}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='RE' THEN '${lbl:b_registry}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='RC' THEN '${lbl:b_received}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='PR' THEN '${lbl:b_received_partial}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='CE' THEN '${lbl:b_closed}'
		WHEN smn_inventario.smn_movimiento_detalle.mde_estatus='PE' THEN '${lbl:b_pending}'
	END AS mde_estatus,
	smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id,
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre AS smn_item_rf,
	smn_inventario.smn_movimiento_detalle.mde_lote,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_solicitada,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida,
	smn_inventario.smn_movimiento_detalle.mde_cantidad_por_recibir,
	CASE
		WHEN (mde_cantidad_por_recibir IS NULL AND mde_cantidad_recibida IS NULL) THEN mde_cantidad_solicitada
		WHEN (mde_cantidad_por_recibir IS NOT NULL AND mde_cantidad_recibida IS NOT NULL) THEN (mde_cantidad_solicitada-(mde_cantidad_recibida+mde_cantidad_por_recibir))
		WHEN mde_cantidad_por_recibir IS NOT NULL THEN (mde_cantidad_solicitada-mde_cantidad_por_recibir)
		WHEN mde_cantidad_por_recibir IS NULL THEN (mde_cantidad_solicitada-mde_cantidad_recibida)
	END AS mde_cantidad_pendiente,
	smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion AS smn_unidad_medida_rf,
	smn_inventario.smn_movimiento_detalle.mde_fecha_registro,
	smn_inventario.smn_movimiento_detalle.mde_fecha_vencimiento_lote,
	smn_inventario.smn_movimiento_detalle.mde_monto_bruto_ml,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_recibida
FROM
	smn_inventario.smn_movimiento_detalle
LEFT OUTER JOIN
	smn_base.smn_item 
ON 
	smn_base.smn_item.smn_item_id = smn_inventario.smn_movimiento_detalle.smn_item_rf
LEFT OUTER JOIN 
	smn_base.smn_unidad_medida 
ON 
	smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_movimiento_detalle.smn_unidad_medida_rf
LEFT OUTER JOIN
	smn_inventario.smn_movimiento_cabecera
ON
	smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id
LEFT OUTER JOIN
	smn_base.smn_almacen
ON
	smn_inventario.smn_movimiento_cabecera.smn_almacen_rf = smn_base.smn_almacen.smn_almacen_id
LEFT OUTER JOIN
	smn_inventario.smn_rol
ON
	smn_inventario.smn_rol.smn_entidades_rf = smn_base.smn_almacen.alm_empresa
LEFT OUTER JOIN
	smn_base.smn_usuarios
ON
	smn_inventario.smn_rol.smn_usuarios_rf = smn_base.smn_usuarios.smn_usuarios_id
LEFT OUTER JOIN
	smn_seguridad.s_user
ON
	smn_seguridad.s_user.user_id = smn_base.smn_usuarios.smn_user_rf
WHERE
	smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id=${fld:id2}
	AND
	smn_seguridad.s_user.userlogin = '${def:user}'
and smn_inventario.smn_movimiento_cabecera.smn_almacen_rf = smn_inventario.smn_rol.smn_almacen_rf