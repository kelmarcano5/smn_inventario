SELECT 
	smn_base.smn_almacen.smn_almacen_id as id,
	smn_base.smn_almacen.alm_codigo||'-'||smn_base.smn_almacen.alm_nombre as item 
FROM smn_base.smn_almacen 
WHERE smn_base.smn_almacen.alm_empresa=${fld:id}