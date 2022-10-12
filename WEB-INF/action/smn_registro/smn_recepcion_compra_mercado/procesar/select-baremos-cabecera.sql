select 
     smn_baremos.smn_baremos_id AS baremo_id,
      case
          when (bar_incremento_precio_it/100) is Null then 0 
          when (bar_incremento_precio_it/100) is not Null then (bar_incremento_precio_it/100)
      end as bar_incremento_precio_it
 
    from smn_base.smn_baremos 
        inner join smn_base.smn_baremos_detalle on 
        smn_base.smn_baremos.smn_baremos_id = smn_base.smn_baremos_detalle.smn_baremos_id

    where smn_base.smn_baremos_detalle.smn_item_rf=${fld:smn_item_rf}  
      and smn_base.smn_baremos_detalle.bad_estatus='A'