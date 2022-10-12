insert into smn_inventario.smn_control_item(
  smn_control_item_id,
  smn_item_id,
  coi_fecha_movimiento,
  smn_almacen_id,
  coi_precio,
  coi_saldo_inicial_existencia,
  coi_cantidad_entradas,
  coi_cantidad_salidas,
  coi_saldo_final_existencia,
  coi_cantidad_reservada,
  coi_cantidad_reserva,
  coi_compras_acordadas,
  coi_valor_inicial,
  coi_valor_entrada,
  coi_valor_salida,
  coi_valor_final,
  coi_costo_promedio,
  coi_ultimo_costo,
  coi_costo_reposicion,
  coi_costo_mas_alto,
  coi_costo_promedio_ponderado,
  coi_idioma,
  coi_usuario,
  coi_fecha_registro,
  coi_hora,
  coi_valor_inicial_ma,
  coi_valor_entradas_ma,
  coi_valor_salidas_ma,
  coi_valor_final_ma,
  coi_costo_promedio_ma,
  coi_ultimo_costo_ma,
  coi_costo_reposicion_ma,
  coi_costo_mas_alto_ma,
  coi_costo_promedio_ponderado_ma,
  coi_precio_ma

)
(Select
nextval('smn_inventario.seq_smn_control_item'),
${fld:smn_item_rf},
${fld:coi_fecha_movimiento},
${fld:smn_almacen_rf},
${fld:coi_precio},
${fld:coi_saldo_inicial_existencia},
${fld:coi_cantidad_entradas},
${fld:coi_cantidad_salidas},
${fld:coi_saldo_final_existencia},
${fld:coi_cantidad_reservada},
${fld:coi_cantidad_reserva},
0,
${fld:coi_valor_inicial},
${fld:coi_valor_entrada},
${fld:coi_valor_salida},
${fld:coi_valor_final},
${fld:coi_costo_promedio},
${fld:coi_ultimo_costo},
0,
${fld:coi_costo_mas_alto},
${fld:coi_costo_promedio_ponderado},
'${def:locale}',
'${def:user}',
{d '${def:date}'},
'${def:time}',
${fld:coi_valor_inicial_ma},
${fld:coi_valor_entrada_ma},
${fld:coi_valor_salida_ma},
${fld:coi_valor_final_ma},
${fld:coi_costo_promedio_ma},
${fld:coi_ultimo_costo_ma},
0,
${fld:coi_costo_mas_alto_ma},
${fld:coi_costo_promedio_ponderado_ma},
${fld:coi_precio_ma}

FROM smn_inventario.smn_movimiento_detalle
WHERE smn_inventario.smn_movimiento_detalle.smn_movimiento_detalle_id = ${fld:smn_movimiento_detalle_id}
)
RETURNING smn_control_item_id as id_control_item