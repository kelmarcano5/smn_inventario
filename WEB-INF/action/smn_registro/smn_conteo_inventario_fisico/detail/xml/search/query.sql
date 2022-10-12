select
		smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
	case
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='AB' then '${lbl:b_open}'
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='CE' then '${lbl:b_closed}'
	when smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='IN' then '${lbl:b_inactive}'
	end as cfi_estatus,
	smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id,
	smn_base.smn_item.itm_codigo||' - '||  smn_base.smn_item.itm_nombre as smn_item_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_cantidad,
	smn_base.smn_unidad_medida.unm_codigo||' - '|| smn_base.smn_unidad_medida.unm_descripcion as smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_costo,
	aux1.aux_codigo ||' - '|| aux1.aux_descripcion as smn_auxiliar_1_rf,
	aux2.aux_codigo ||' - '|| aux2.aux_descripcion as smn_auxiliar_2_rf,
	smn_inventario.smn_conteo_inventario_fisico.cfi_estatus,
	smn_inventario.smn_conteo_inventario_fisico.cfi_fecha_registro
	
from
	smn_inventario.smn_conteo_inventario_fisico
	inner join smn_base.smn_auxiliar aux1 on aux1.smn_auxiliar_id = 	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_1_rf
	left outer join smn_base.smn_auxiliar aux2 on aux2.smn_auxiliar_id = 	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_2_rf
	inner join smn_base.smn_unidad_medida on smn_base.smn_unidad_medida.smn_unidad_medida_id = smn_inventario.smn_conteo_inventario_fisico.smn_unidad_medida_almacenaje_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_conteo_inventario_fisico.smn_item_id
	

where
	smn_conteo_inventario_fisico_id = ${fld:id}
