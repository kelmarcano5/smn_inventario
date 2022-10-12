update smn_inventario.smn_control_item
  set
  coi_precio=${fld:coi_precio},
  coi_saldo_inicial_existencia=${fld:coi_saldo_inicial_existencia},
  coi_cantidad_entradas=${fld:coi_cantidad_entradas},
  coi_cantidad_salidas=${fld:coi_cantidad_salidas},
  coi_saldo_final_existencia=${fld:coi_saldo_final_existencia},
  coi_valor_inicial=${fld:coi_valor_inicial},
  coi_valor_entrada=${fld:coi_valor_entrada},
  coi_valor_salida=${fld:coi_valor_salida},
  coi_valor_final=${fld:coi_valor_final},
  coi_costo_promedio=${fld:coi_costo_promedio},
  coi_ultimo_costo=${fld:coi_ultimo_costo},
  coi_costo_mas_alto=${fld:coi_costo_mas_alto},
  coi_idioma='${def:locale}',
  coi_usuario='${def:user}',
  coi_fecha_registro={d '${def:date}'},
  coi_hora='${def:time}',
  coi_costo_promedio_ponderado=${fld:coi_costo_promedio_ponderado}
  
  where smn_control_item_id=${fld:smn_control_item_id}
