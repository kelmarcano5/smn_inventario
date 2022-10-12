select
	smn_despacho_detalle_id,
	smn_despacho_id,
	itm_codigo ||' - '|| itm_nombre as smn_item_rf,
	dde_cantidad_solicitada,
	dde_cantidad_despachada,
	dde_costo_ml,
	dde_costo_ma,
	dde_motivo,
	--usr_nombres ||' - '|| usr_apellidos as smn_usuario_aprobador_rf,
	dde_fecha_aprobacion,
	dde_fecha_cierre,
	case
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='GE' then '${lbl:b_generated}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='DE' then '${lbl:b_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='RE' then '${lbl:b_rejected}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='PD' then '${lbl:b_partially_delivered}'
	when smn_inventario.smn_despacho_detalle.dde_estatus_transaccion='CE' then '${lbl:b_closed}'
	end as dde_estatus_transaccion,
	dde_idioma,
	dde_usuario,
	dde_fecha_registro,
	dde_hora
from
	smn_inventario.smn_despacho_detalle
	inner join smn_base.smn_item on smn_item_id = smn_inventario.smn_despacho_detalle.smn_caracteristica_item_id
	--inner join smn_base.smn_v_usuarios on smn_usuarios_id = smn_usuario_aprobador_rf

where
smn_inventario.smn_despacho_detalle.smn_despacho_id = ${fld:id2}
