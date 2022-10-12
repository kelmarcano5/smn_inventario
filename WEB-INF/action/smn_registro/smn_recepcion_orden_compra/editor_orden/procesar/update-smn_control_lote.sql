UPDATE smn_inventario.smn_control_lote SET
  col_cantidad_inicial=${fld:coi_saldo_inicial_existencia},
  col_entradas=${fld:coi_cantidad_entradas},
  col_salidas=${fld:coi_cantidad_salidas},
  col_cantidad_final=${fld:coi_saldo_final_existencia},
  col_idioma='${def:locale}',
  col_usuario='${def:user}',
  col_hora='${def:time}'
WHERE
	smn_control_lote_id = ${fld:smn_control_item_id}