select
	smn_base.smn_item.itm_codigo ||' - '|| smn_base.smn_item.itm_nombre as smn_item_rf_combo,
	(select usr_nombres ||' - '||usr_apellidos from  smn_base.smn_v_usuarios  where smn_usuarios_id is not null  and smn_usuarios_id=smn_inventario.smn_despacho_detalle.smn_usuario_aprobador_rf  order by smn_usuarios_id ) as smn_usuario_aprobador_rf_combo,
	case
		when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='GE' then '${lbl:b_generated}'
		when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='DE' then '${lbl:b_delivered}'
		when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='RE' then '${lbl:b_rejected}'
		when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='PD' then '${lbl:b_partially_delivered}'
		when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='CE' then '${lbl:b_closed}'
	end as dde_estatus_transaccion_combo,
	smn_inventario.smn_despacho_detalle.*
from 
	smn_inventario.smn_despacho
	inner join smn_inventario.smn_despacho_detalle on smn_inventario.smn_despacho_detalle.smn_despacho_id = smn_inventario.smn_despacho.smn_despacho_id
	inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_caracteristica_item_id = smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id
	inner join smn_base.smn_item on smn_base.smn_item.smn_item_id = smn_inventario.smn_caracteristica_item.smn_item_rf
where
	smn_inventario.smn_despacho_detalle.smn_despacho_id=smn_inventario.smn_despacho.smn_despacho_id and 
	smn_inventario.smn_despacho.smn_despacho_id=${fld:id}
