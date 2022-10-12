SELECT 
	smn_base.smn_almacen.smn_almacen_id as id,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as item 
FROM smn_base.smn_almacen 
WHERE smn_base.smn_almacen.alm_empresa=(
	SELECT smn_base.smn_entidades.smn_entidades_id
	FROM smn_base.smn_entidades
	WHERE smn_base.smn_entidades.smn_entidades_id=${fld:id}) 