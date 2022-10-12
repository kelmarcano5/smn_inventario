SELECT
	smn_inventario.smn_transporte.smn_transporte_id,
	smn_inventario.smn_transporte.tra_codigo,
	smn_inventario.smn_transporte.tra_descripcion_transporte,
	case
	when smn_inventario.smn_transporte.tra_tipo_transporte='IN' then '${lbl:b_interior}'
	when smn_inventario.smn_transporte.tra_tipo_transporte='EX' then '${lbl:b_external}'
	end as tra_tipo_transporte,
	smn_base.smn_activo_fijo.acf_codigo ||' - '|| smn_base.smn_activo_fijo.acf_nombre AS smn_activo_rf,
	smn_base.smn_auxiliar.aux_codigo ||' - '|| smn_base.smn_auxiliar.aux_descripcion AS smn_proveedor_rf,
	case
	when smn_inventario.smn_transporte.tra_estatus='AC' then '${lbl:b_active}'
	when smn_inventario.smn_transporte.tra_estatus='IN' then '${lbl:b_inactive}'
	end as tra_estatus,
	smn_inventario.smn_transporte.tra_vigencia,
	smn_inventario.smn_transporte.tra_idioma,
	smn_inventario.smn_transporte.tra_usuario,
	smn_inventario.smn_transporte.tra_fecha_registro,
	smn_inventario.smn_transporte.tra_hora
FROM
	smn_inventario.smn_transporte
	LEFT OUTER JOIN smn_base.smn_activo_fijo ON smn_base.smn_activo_fijo.smn_afijo_id = smn_inventario.smn_transporte.smn_activo_rf
	LEFT OUTER JOIN smn_compras.smn_proveedor ON smn_compras.smn_proveedor.smn_proveedor_id = smn_inventario.smn_transporte.smn_proveedor_rf
	LEFT OUTER JOIN smn_base.smn_auxiliar ON smn_base.smn_auxiliar.smn_auxiliar_id = smn_compras.smn_proveedor.smn_auxiliar_rf
where
	smn_transporte_id is not null
	${filter}
order by
		smn_transporte_id
