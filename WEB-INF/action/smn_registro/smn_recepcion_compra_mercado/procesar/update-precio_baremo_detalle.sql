update smn_base.smn_baremos_detalle set

    bad_precio_moneda_local = ${fld:precio_ml},

    bad_precio_moneda_alterna=${fld:precio_ma}

     where smn_base.smn_baremos_detalle.smn_item_rf=${fld:smn_item_rf}  
      and smn_base.smn_baremos_detalle.bad_estatus='A'
      and smn_base.smn_baremos_detalle.smn_baremos_id=${fld:baremo_id} 

-- update smn_base.smn_baremos_detalle set

--     bad_precio_moneda_local = ${fld:precio_ml},

--     bad_precio_moneda_alterna=${fld:precio_ma}
  
  -- from 
  --     (SELECT
  --       case  
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CP' and smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then smn_inventario.smn_control_item.coi_costo_promedio
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='UC' and smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then smn_inventario.smn_control_item.coi_ultimo_costo
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CR' and smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then smn_inventario.smn_control_item.coi_costo_reposicion
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CA' and smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then smn_inventario.smn_control_item.coi_costo_mas_alto
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CD' and smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then smn_inventario.smn_control_item.coi_costo_promedio_ponderado
  --         when (smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then 0
  --       end as costo_ml,
  --       case
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CP' and smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then smn_inventario.smn_control_item.coi_costo_promedio_ma
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='UC' and smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then smn_inventario.smn_control_item.coi_ultimo_costo_ma
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CR' and smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then smn_inventario.smn_control_item.coi_costo_reposicion_ma
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CA' and smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then smn_inventario.smn_control_item.coi_costo_mas_alto_ma
  --         when (smn_inventario.smn_caracteristica_item.cit_valoracion_inventario='CD' and smn_inventario.smn_caracteristica_item.cit_tipo_costo='MA') then smn_inventario.smn_control_item.coi_costo_promedio_ponderado_ma
  --         when (smn_inventario.smn_caracteristica_item.cit_tipo_costo<>'MA') then 0
  --       end as costo_ma
  --     FROM smn_inventario.smn_control_item 
  --     inner join smn_inventario.smn_caracteristica_item on 
  --       smn_inventario.smn_caracteristica_item.smn_item_rf=smn_inventario.smn_control_item.smn_item_id
  --       WHERE smn_inventario.smn_control_item.smn_item_id=${fld:smn_item_rf}) as subqueryCosto, 
      

  --     (select (cit_margen_precio/100) as margen
  --       from smn_inventario.smn_caracteristica_item
  --       where smn_item_rf=${fld:smn_item_rf}) as subquery1,

  --     (select 
  --         distinct(smn_baremos.smn_baremos_id) AS baremo_id,
  --         case
  --           when (bar_incremento_precio_it/100) is Null then 0 
  --           when (bar_incremento_precio_it/100) is not Null then (bar_incremento_precio_it/100)
  --         end as bar_incremento_precio_it
  --       from smn_base.smn_baremos 
  --       inner join smn_base.smn_baremos_detalle on 
  --       smn_base.smn_baremos.smn_baremos_id = smn_base.smn_baremos_detalle.smn_baremos_id
  --       where smn_base.smn_baremos_detalle.smn_item_rf=${fld:smn_item_rf}  
  --             and smn_base.smn_baremos_detalle.bad_estatus='A') as subquery2
 
     -- where smn_base.smn_baremos_detalle.smn_item_rf=${fld:smn_item_rf}  
     --  and smn_base.smn_baremos_detalle.bad_estatus='A'
     --  and smn_base.smn_baremos_detalle.smn_baremos_id=${fld:baremo_id}       