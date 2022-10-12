select DISTINCT smn_base.smn_unidad_medida.smn_unidad_medida_id as id, smn_base.smn_unidad_medida.unm_codigo||'-'||smn_base.smn_unidad_medida.unm_descripcion as item 
from smn_base.smn_unidad_medida 
inner join smn_inventario.smn_caracteristica_item on smn_inventario.smn_caracteristica_item.smn_unidad_medida_almacenamiento_rf = smn_base.smn_unidad_medida.smn_unidad_medida_id