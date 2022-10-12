select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
select
		smn_inventario.smn_movimiento_cabecera.smn_movimiento_cabecera_id,
	case
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='PE' then '${lbl:b_pending}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='AP' then '${lbl:b_aprobated}'
	when smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso='RE' then '${lbl:b_refused}'
	end as mca_estatus_proceso,
	smn_inventario.smn_movimiento_cabecera.smn_entidad_rf,
	smn_inventario.smn_movimiento_cabecera.smn_almacen_rf,
	smn_inventario.smn_movimiento_cabecera.smn_modulo_rf,
	smn_inventario.smn_movimiento_cabecera.smn_documento_origen_rf,
	smn_inventario.smn_movimiento_cabecera.mca_numero_documento_origen,
	smn_inventario.smn_movimiento_cabecera.smn_documento_id,
	smn_inventario.smn_movimiento_cabecera.mca_numero,
	smn_inventario.smn_movimiento_cabecera.mca_estatus_proceso,
	smn_inventario.smn_movimiento_cabecera.mca_fecha_registro
	
from
	smn_inventario.smn_movimiento_cabecera
