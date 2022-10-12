select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
	case
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='AB' then '${lbl:b_open}'
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='CE' then '${lbl:b_closed}'
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='IN' then '${lbl:b_inactive}'
	end as cfi_estatus,
	smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id,
	smn_inventario.smn_conteo_inventario_fisico.smn_item_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_cantidad,
	smn_inventario.smn_conteo_inventario_fisico.smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_costo,
	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_1_rf,
	smn_inventario.smn_conteo_inventario_fisico.cfi_estatus,
	smn_inventario.smn_conteo_inventario_fisico.cfi_fecha_registro
	
from
	smn_inventario.smn_conteo_inventario_fisico
