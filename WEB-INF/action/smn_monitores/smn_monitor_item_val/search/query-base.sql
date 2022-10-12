select smn_base.smn_item.itm_codigo as codigo,
		smn_base.smn_item.itm_nombre as nombre,
	   smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	   smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	   smn_inventario.smn_control_item.smn_control_item_id,
	   smn_inventario.smn_control_item.coi_fecha_registro,
		 smn_inventario.smn_control_item.coi_saldo_inicial_existencia,
		 smn_inventario.smn_control_item.coi_saldo_final_existencia,
		 smn_inventario.smn_control_item.coi_cantidad_entradas,
		 smn_inventario.smn_control_item.coi_cantidad_salidas,
		 smn_inventario.smn_control_item.coi_valor_inicial,
		 smn_inventario.smn_control_item.coi_valor_entrada,
		 smn_inventario.smn_control_item.coi_valor_salida,
		 smn_inventario.smn_control_item.coi_valor_final,
		  case 
		 when smn_inventario.smn_control_item.coi_valor_inicial_ma is null then 0 else  smn_inventario.smn_control_item.coi_valor_inicial_ma
		 end as coi_valor_inicial_ma,
		  case 
		 when smn_inventario.smn_control_item.coi_valor_entradas_ma is null then 0 else  smn_inventario.smn_control_item.coi_valor_entradas_ma
		 end as coi_valor_entradas_ma,
		  case 
		 when smn_inventario.smn_control_item.coi_valor_salidas_ma is null then 0 else  smn_inventario.smn_control_item.coi_valor_salidas_ma
		 end as coi_valor_salidas_ma,
		  case 
		 when smn_inventario.smn_control_item.coi_valor_final_ma is null then 0 else  smn_inventario.smn_control_item.coi_valor_final_ma
		 end as coi_valor_final_ma,
		 smn_inventario.smn_control_item.coi_fecha_movimiento
from smn_inventario.smn_control_item
	 inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_control_item.smn_almacen_id
	 left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_base.smn_almacen.alm_empresa
	 left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_base.smn_almacen.alm_sucursal
	 inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_control_item.smn_item_id
where smn_inventario.smn_control_item.smn_control_item_id is not null
	${filter}
ORDER BY smn_base.smn_entidades.ent_descripcion_corta, smn_base.smn_almacen.alm_nombre, smn_base.smn_item.itm_nombre, smn_inventario.smn_control_item.coi_fecha_movimiento DESC