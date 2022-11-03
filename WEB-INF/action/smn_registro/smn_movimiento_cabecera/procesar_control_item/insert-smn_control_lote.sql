INSERT INTO smn_inventario.smn_control_lote
(
	smn_control_lote_id,
  	smn_entidad_rf,
  	smn_sucursal_rf,
  	smn_caracteristica_almacen_id,
  	smn_caracteristica_item_id,
  	col_lote,
  	col_fecha_vencimiento,
  	col_cantidad_inicial,
  	col_entradas,
  	col_salidas,
  	col_cantidad_final,
  	col_idioma,
  	col_usuario,
  	col_fecha_registro,
  	col_hora
)
VALUES
(
	nextval('smn_inventario.seq_smn_control_lote'), --smn_control_lote_id
  	${fld:smn_entidad_rf}, --smn_entidad_rf
  	${fld:smn_sucursal_rf}, --smn_sucursal_rf
  	${fld:smn_caracteristica_almacen_id}, --smn_caracteristica_almacen_id
  	${fld:smn_caracteristica_item_id}, --smn_caracteristica_item_id
  	${fld:mde_lote}, --col_lote
  	${fld:mde_fecha_vencimiento_lote}, --col_fecha_vencimiento
  	${fld:coi_valor_inicial}, --col_cantidad_inicial
  	${fld:coi_valor_entrada}, --col_entradas
  	${fld:coi_valor_salida}, --col_salidas
  	${fld:coi_valor_final}, --col_cantidad_final
  	'${def:locale}', --col_idioma
  	'${def:user}', --col_usuario
 	{d '${def:date}'}, --col_fecha_registro
  	'${def:time}' --col_hora
)