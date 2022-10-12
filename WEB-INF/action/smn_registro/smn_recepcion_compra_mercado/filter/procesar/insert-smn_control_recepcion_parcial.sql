INSERT INTO smn_inventario.smn_control_recepcion_parcial
(
	smn_control_recepcion_id,
  	smn_movimiento_cabecera_id,
  	smn_movimiento_detalle_id,
	smn_orden_compra_rf,
  	crp_numero_documento,
  	smn_item_id,
  	crp_cantidad_recibida,
  	crp_fecha_recepcion,
    crp_lote,
    crp_fecha_vencimiento_lote,
  	crp_idioma,
  	crp_usuario,
  	crp_fecha_registro,
  	crp_hora
)
VALUES
(
	nextval('smn_inventario.seq_smn_control_recepcion_parcial'), --smn_control_recepcion_id
  	${fld:smn_movimiento_cabecera_id}, --smn_movimiento_cabecera_id
  	${fld:smn_movimiento_detalle_id}, --smn_movimiento_detalle_id
	${fld:smn_orden_compra_rf}, --smn_orden_compra_rf
  	${fld:crp_numero_documento}, --crp_numero_documento
  	${fld:smn_item_rf}, --smn_item_id
  	${fld:mde_cantidad_por_recibir}, --crp_cantidad_recibida
  	{d '${def:date}'}, --crp_fecha_recepcion
    ${fld:mde_lote}, --crp_lote
    ${fld:mde_fecha_vencimiento_lote}, --crp_fecha_vencimiento_lote
  	'${def:locale}', --crp_idioma
  	'${def:user}', --crp_usuario
  	{d '${def:date}'}, --crp_fecha_registro
  	'${def:time}' --crp_hora
)