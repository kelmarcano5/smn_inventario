select smn_base.smn_item.itm_nombre as smn_item_rf,
	   smn_base.smn_entidades.ent_descripcion_corta as smn_entidad_rf,
	   smn_base.smn_almacen.alm_nombre as smn_almacen_rf,
	   smn_inventario.smn_control_item.smn_control_item_id,
	   smn_inventario.smn_control_item.coi_fecha_registro,
		 smn_inventario.smn_control_item.coi_saldo_inicial_existencia,
		 smn_inventario.smn_control_item.coi_saldo_final_existencia,
		 smn_inventario.smn_control_item.coi_cantidad_entradas,
		 smn_inventario.smn_control_item.coi_cantidad_salidas,
		 smn_inventario.smn_control_item.coi_valor_inicial,
		 smn_inventario.smn_control_item.coi_valor_final,
		 smn_inventario.smn_control_item.coi_fecha_movimiento,
		 		 smn_inventario.smn_control_item.coi_cantidad_reservada
from smn_inventario.smn_control_item
	 inner join smn_base.smn_almacen on smn_base.smn_almacen.smn_almacen_id = smn_inventario.smn_control_item.smn_almacen_id
	 left outer join smn_base.smn_entidades on smn_base.smn_entidades.smn_entidades_id = smn_base.smn_almacen.alm_empresa
	 left outer join smn_base.smn_sucursales on smn_base.smn_sucursales.smn_sucursales_id = smn_base.smn_almacen.alm_sucursal
	 inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_control_item.smn_item_id
where smn_inventario.smn_control_item.smn_control_item_id is not null
	${filter}
ORDER BY smn_inventario.smn_control_item.coi_fecha_movimiento DESC