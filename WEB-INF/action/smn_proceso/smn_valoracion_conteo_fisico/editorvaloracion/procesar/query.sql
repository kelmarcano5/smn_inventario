with rows as (
select 
	smn_inventario.smn_conteo.smn_almacen_rf,
	smn_inventario.smn_conteo_inventario_fisico.smn_conteo_inventario_fisico_id,
	smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id,
	smn_inventario.smn_conteo_inventario_fisico.smn_item_id,
	smn_inventario.smn_conteo_inventario_fisico.cfi_cantidad,
	smn_inventario.smn_conteo_inventario_fisico.smn_unidad_medida_almacenaje_id,
	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_1_rf,
	smn_inventario.smn_conteo_inventario_fisico.smn_auxiliar_2_rf,
	smn_inventario.smn_conteo_inventario_fisico.cfi_estatus,
	case 
		when smn_inventario.smn_control_item.coi_saldo_final_existencia is null then 0 else smn_inventario.smn_control_item.coi_saldo_final_existencia
	end as coi_saldo_final_existencia,
	case 
		when smn_inventario.smn_control_item.coi_costo_promedio is null then 0 else smn_inventario.smn_control_item.coi_costo_promedio
	end as coi_costo_promedio,
	CAST(${fld:vcf_fecha_generacion} as varchar) as vcf_fecha_generacion,
	${fld:smn_documento_id} as smn_documento_id
from 
	smn_inventario.smn_conteo_inventario_fisico
	inner join smn_inventario.smn_conteo on smn_inventario.smn_conteo.smn_conteo_id = smn_inventario.smn_conteo_inventario_fisico.smn_conteo_id
	inner join smn_inventario.smn_caracteristica_almacen on smn_inventario.smn_caracteristica_almacen.smn_almacen_rf = smn_inventario.smn_conteo.smn_almacen_rf
	left outer join smn_inventario.smn_control_item on smn_inventario.smn_control_item.smn_item_id=smn_inventario.smn_conteo_inventario_fisico.smn_item_id
where
	smn_inventario.smn_conteo_inventario_fisico.cfi_estatus='AP' and smn_inventario.smn_caracteristica_almacen.smn_almacen_rf=${fld:smn_almacen_rf}
)
select * from rows;


