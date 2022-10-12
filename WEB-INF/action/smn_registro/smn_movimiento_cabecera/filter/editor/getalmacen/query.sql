SELECT 
smn_base.smn_entidades.smn_entidades_id as id,
smn_base.smn_entidades.ent_codigo||'-'||smn_base.smn_entidades.ent_descripcion_corta as item 
from smn_base.smn_entidades
where smn_base.smn_entidades.ent_corporacion=(SELECT smn_base.smn_corporaciones.smn_corporaciones_id
from smn_base.smn_corporaciones
where
upper(smn_base.smn_corporaciones.crp_nombre)=upper(${fld:id})) 