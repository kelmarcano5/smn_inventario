INSERT INTO smn_inventario.smn_movimiento_cabecera
(
	smn_movimiento_cabecera_id,
	smn_entidad_rf,
	smn_sucursal_rf,
	smn_almacen_rf,
	smn_modulo_rf,
	smn_documento_origen_rf,
	smn_documento_id,
	mca_fecha_recibida,
	mca_estatus_proceso,
	mca_estatus_financiero,
	mca_idioma,
	mca_usuario,
	mca_fecha_registro,
	mca_hora
)
VALUES
(
	${seq:nextval@smn_inventario.seq_smn_movimiento_cabecera},
	(select smn_base.smn_entidades.smn_entidades_id as smn_entidad_rf from smn_base.smn_entidades inner join smn_base.smn_almacen on smn_base.smn_almacen.alm_empresa = smn_base.smn_entidades.smn_entidades_id where smn_base.smn_almacen.smn_almacen_id=${fld:almacen} limit 1),
	(select case when smn_base.smn_sucursales.smn_sucursales_id is null then 0 else smn_base.smn_sucursales.smn_sucursales_id end as smn_sucursal_rf from smn_base.smn_entidades left join smn_base.smn_almacen on smn_base.smn_almacen.alm_empresa = smn_base.smn_entidades.smn_entidades_id left join smn_base.smn_sucursales on smn_base.smn_sucursales.suc_empresa = smn_base.smn_almacen.alm_empresa where smn_base.smn_almacen.smn_almacen_id=${fld:almacen} limit 1),
	(select smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf as almacen from smn_inventario.smn_valoracion_conteo_fisico where smn_inventario.smn_valoracion_conteo_fisico.smn_almacen_rf=${fld:almacen} limit 1),
	'9',
	(select smn_base.smn_documentos_generales.smn_documentos_generales_id from smn_base.smn_documentos_generales inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_general_rf = smn_base.smn_documentos_generales.smn_documentos_generales_id
	 where smn_inventario.smn_documento.smn_documento_id=${fld:documento}),
	${fld:documento},
	${fld:fecha},
	'GE',
	'PE',
	'${def:locale}',
	'${def:user}',
	{d '${def:date}'},
	'${def:time}'
);

