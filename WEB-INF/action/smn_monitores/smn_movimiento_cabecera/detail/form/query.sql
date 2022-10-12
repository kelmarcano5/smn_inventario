select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_refused}'
	end as mca_estatus_proceso,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PA' then '${lbl:b_payment}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_financiero='PP' then '${lbl:b_partial_payment}'
	end as mca_estatus_financiero,
smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as smn_modulo_rf,
	smn_base.smn_documentos_generales.dcg_codigo||'-'||smn_base.smn_documentos_generales.dcg_descripcion as smn_documento_origen_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen,
	smn_inventario.smn_movimiento_cabecera.smn_documento_id,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro,
	smn_inventario.smn_movimiento_cabecera.*

	
from
	smn_inventario.smn_movimiento_cabecera
INNER JOIN smn_base.smn_entidades ON (smn_base.smn_entidades.smn_entidades_id = smn_inventario.smn_movimiento_cabecera.smn_entidad_rf)
left outer JOIN smn_base.smn_sucursales ON (smn_base.smn_sucursales.smn_sucursales_id = smn_inventario.smn_movimiento_cabecera.smn_sucursal_rf)
LEFT OUTER JOIN  smn_base.smn_almacen ON (smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf)
LEFT OUTER JOIN smn_base.smn_modulos ON (smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf)
LEFT OUTER JOIN smn_base.smn_documentos_generales ON (smn_base.smn_documentos_generales.smn_documentos_generales_id = smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf)
where
	smn_movimiento_cabecera_id = ${fld:id}
