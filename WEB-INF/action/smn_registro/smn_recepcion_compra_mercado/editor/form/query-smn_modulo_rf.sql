select smn_base.smn_modulos.smn_modulos_id as id, smn_base.smn_modulos.mod_codigo||'-'||smn_base.smn_modulos.mod_nombre as item from smn_base.smn_modulos
where smn_base.smn_modulos.mod_codigo='INV'