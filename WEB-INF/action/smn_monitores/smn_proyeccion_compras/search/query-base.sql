with rows as (
	SELECT DISTINCT
		(select SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) from smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
		inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
		left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
		where smn_inventario.smn_tipo_documento.tdc_salidas_ventas = 'SI' ${filter}) as cantidad_venta,

		(select CASE WHEN SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) IS NULL THEN 0 ELSE SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) END as cantidad_consumo from smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
		inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
		left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
		where smn_inventario.smn_tipo_documento.tdc_salidas_consumos = 'SI' ${filter}) as cantidad_consumo,
		
				(select CASE WHEN SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) IS NULL THEN 0 ELSE SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) END as cantidad_pedido from smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
		inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
		left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
		where smn_base.smn_modulos.mod_codigo='CME' AND smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' ${filter}) as cantidad_pedido,
		
		(select CASE WHEN smn_inventario.smn_control_item.coi_saldo_final_existencia IS NULL THEN 0 ELSE smn_inventario.smn_control_item.coi_saldo_final_existencia END as cantidad_existencia from smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
		inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
		inner join smn_inventario.smn_control_item on smn_inventario.smn_control_item.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf 
		left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf where smn_inventario.smn_control_item.coi_fecha_movimiento >= ${fld:ped_fecha_registro} and smn_inventario.smn_control_item.coi_fecha_movimiento<=CURRENT_DATE LIMIT 1) as cantidad_existencia,
		
		(select CASE WHEN SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_por_recibir) IS NULL THEN 0 ELSE SUM(smn_inventario.smn_movimiento_detalle.mde_cantidad_recibida) END as cantidad_consumo from smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_documento on smn_inventario.smn_documento.smn_documento_id = smn_inventario.smn_movimiento_cabecera.smn_documento_id
		inner join smn_inventario.smn_tipo_documento on smn_inventario.smn_tipo_documento.smn_tipo_documento_id = smn_inventario.smn_documento.smn_tipo_documento_id
		inner join smn_inventario.smn_control_item on smn_inventario.smn_control_item.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf 
		left join smn_base.smn_modulos on smn_base.smn_modulos.smn_modulos_id = smn_inventario.smn_movimiento_cabecera.smn_modulo_rf
		where smn_inventario.smn_movimiento_cabecera.smn_modulo_rf=3 and smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='GE' and smn_inventario.smn_movimiento_cabecera.mca_fecha_recibida>=${fld:pch_fecha_registro} and smn_inventario.smn_movimiento_cabecera.mca_fecha_recibida<=CURRENT_DATE LIMIT 1) as compras_colocadas,
		smn_base.smn_item.itm_codigo,
		smn_base.smn_item.itm_nombre,
		smn_base.smn_item.smn_item_id as iditem,
		0 as sugerencia
	FROM
		smn_inventario.smn_movimiento_cabecera
		inner join smn_inventario.smn_movimiento_detalle ON smn_inventario.smn_movimiento_detalle.smn_movimiento_cabecera_id = smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id
		inner join smn_inventario.smn_control_item on smn_inventario.smn_control_item.smn_almacen_id = smn_inventario.smn_movimiento_cabecera.smn_almacen_rf 
		left join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_control_item.smn_almacen_id
	    left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_base.smn_almacen.alm_empresa
		left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_base.smn_almacen.alm_sucursal
		inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_control_item.smn_item_id
	where smn_inventario.smn_control_item.smn_control_item_id is not null
		${filter}
)
select rows.cantidad_venta, rows.cantidad_consumo, rows.cantidad_pedido, rows.cantidad_existencia, rows.compras_colocadas, rows.itm_codigo, rows.itm_nombre,
rows.cantidad_venta + rows.cantidad_pedido + rows.cantidad_consumo as sugerencia, rows.iditem
from rows


