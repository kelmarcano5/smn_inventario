select smn_base.smn_item.itm_codigo as codigo,
		smn_base.smn_item.itm_nombre as nombre,
	   smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	   smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	   smn_inventario.smn_control_item.smn_control_item_id,
	   smn_inventario.smn_control_item.coi_fecha_registro,
		 smn_inventario.smn_control_item.coi_costo_promedio,
		 smn_inventario.smn_control_item.coi_ultimo_costo,
		 smn_inventario.smn_control_item.coi_costo_reposicion,
		 smn_inventario.smn_control_item.coi_costo_mas_alto,
		 smn_inventario.smn_control_item.coi_costo_promedio_ponderado,
		 case 
		 when smn_inventario.smn_control_item.coi_costo_promedio_ma is null then 0 else  smn_inventario.smn_control_item.coi_costo_promedio_ma
		 end as coi_costo_promedio_ma,
		 case 
		 when smn_inventario.smn_control_item.coi_ultimo_costo_ma is null then 0 else  smn_inventario.smn_control_item.coi_ultimo_costo_ma
		 end as coi_ultimo_costo_ma,
		 case 
		 when smn_inventario.smn_control_item.coi_costo_reposicion_ma is null then 0 else  smn_inventario.smn_control_item.coi_costo_reposicion_ma
		 end as coi_costo_reposicion_ma,
		 case 
		 when smn_inventario.smn_control_item.coi_costo_mas_alto_ma is null then 0 else  smn_inventario.smn_control_item.coi_costo_mas_alto_ma
		 end as coi_costo_mas_alto_ma,
		 case 
		 when smn_inventario.smn_control_item.coi_costo_promedio_ponderado_ma is null then 0 else  smn_inventario.smn_control_item.coi_costo_promedio_ponderado_ma
		 end as coi_costo_promedio_ponderado_ma,
		 smn_inventario.smn_control_item.coi_fecha_movimiento
from smn_inventario.smn_control_item
	 inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_control_item.smn_almacen_id
	 left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_base.smn_almacen.alm_empresa
	 left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_base.smn_almacen.alm_sucursal
	 inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_control_item.smn_item_id
where smn_inventario.smn_control_item.smn_control_item_id is not null
	${filter}
ORDER BY smn_base.smn_entidades.ent_descripcion_corta, smn_base.smn_almacen.alm_nombre, smn_base.smn_item.itm_nombre, smn_inventario.smn_control_item.coi_fecha_movimiento DESC