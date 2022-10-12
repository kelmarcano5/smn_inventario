SELECT 
	smn_base.smn_sucursales.smn_sucursales_id as id,
	smn_base.smn_sucursales.suc_codigo||'-'||smn_base.smn_sucursales.suc_nombre as item 
FROM smn_base.smn_sucursales 
WHERE smn_base.smn_sucursales.suc_empresa=(
	SELECT smn_base.smn_entidades.smn_entidades_id
	FROM smn_base.smn_entidades
	WHERE smn_base.smn_entidades.smn_entidades_id=${fld:id}) 