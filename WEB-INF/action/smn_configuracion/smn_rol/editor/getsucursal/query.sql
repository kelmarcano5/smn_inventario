SELECT 
smn_base.smn_sucursales.smn_sucursales_id as id,
smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as item
, * 
from smn_base.smn_sucursales, smn_base.smn_entidades
where smn_base.smn_entidades.smn_entidades_id = smn_base.smn_sucursales.suc_empresa
  and smn_base.smn_entidades.smn_entidades_id = ${fld:id2}