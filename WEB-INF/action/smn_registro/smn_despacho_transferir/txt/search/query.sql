select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
select
		smn_inventario.smn_despacho.smn_despacho_id,
	case
	when smn_inventario.smn_despacho.des_estatus='GE' then '${lbl:b_generada}'
	when smn_inventario.smn_despacho.des_estatus='TR' then '${lbl:b_transporte}'
	when smn_inventario.smn_despacho.des_estatus='AP' then '${lbl:b_aprobada}'
	when smn_inventario.smn_despacho.des_estatus='RE' then '${lbl:b_rechazada}'
	when smn_inventario.smn_despacho.des_estatus='EN' then '${lbl:b_entregada}'
	when smn_inventario.smn_despacho.des_estatus='PE' then '${lbl:b_parcialmente_entregada}'
	when smn_inventario.smn_despacho.des_estatus='CE' then '${lbl:b_cerrada}'
	end as des_estatus_combo,
	smn_inventario.smn_despacho.smn_modulo_rf,
	smn_inventario.smn_despacho.smn_documento_origen_id,
	smn_inventario.smn_despacho.des_numero_documento_origen,
	smn_inventario.smn_despacho.smn_documento_id,
	smn_inventario.smn_despacho.des_numero_documento,
	smn_inventario.smn_despacho.des_descripcion,
	smn_inventario.smn_despacho.smn_entidad_rf,
	smn_inventario.smn_despacho.smn_clase_auxiliar_rf,
	smn_inventario.smn_despacho.smn_auxiliar_rf,
	smn_inventario.smn_despacho.smn_unidad_organizativa_rf,
	smn_inventario.smn_despacho.des_fecha_despacho,
	smn_inventario.smn_despacho.des_estatus,
	smn_inventario.smn_despacho.des_fecha_registro
	
from
	smn_inventario.smn_despacho
