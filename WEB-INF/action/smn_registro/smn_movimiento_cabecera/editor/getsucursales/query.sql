SELECT 
smn_base.smn_sucursales.smn_sucursales_id as id,
smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as item 
from smn_base.smn_sucursales 
where smn_base.smn_sucursales.suc_empresa=(SELECT smn_base.smn_entidades.smn_entidades_id
from smn_base.smn_entidades
where
upper(smn_base.smn_entidades.ent_descripcion_corta)=upper(${fld:id})) 