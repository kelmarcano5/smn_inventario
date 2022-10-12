Select 
smn_base.smn_entidades.smn_entidades_id as id,
smn_base.smn_entidades.ent_codigo||'-'||smn_base.smn_entidades.ent_descripcion_larga as item
from smn_base.smn_entidades, smn_base.smn_corporaciones
where smn_base.smn_entidades.ent_corporacion = smn_base.smn_corporaciones.smn_corporaciones_id
and  smn_base.smn_corporaciones.smn_corporaciones_id = ${fld:id1}